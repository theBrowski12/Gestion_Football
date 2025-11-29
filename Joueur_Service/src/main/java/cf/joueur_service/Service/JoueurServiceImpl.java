package cf.joueur_service.Service;

import cf.joueur_service.DTOs.RequestJoueurDTO;
import cf.joueur_service.DTOs.ResponseEquipDTO;
import cf.joueur_service.DTOs.ResponseJoueurDTO;
import cf.joueur_service.Entity.Joueur;
import cf.joueur_service.FeignClient.EquipClient;
import cf.joueur_service.Mappers.JoueurMapper;
import cf.joueur_service.Repository.JoueurRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JoueurServiceImpl implements JoueurService {
    private final JoueurRepository repo;
    private final JoueurMapper mapper;
    private final EquipClient equipClient;

    public JoueurServiceImpl(JoueurRepository repo, JoueurMapper mapper, EquipClient equipClient) {
        this.repo = repo;
        this.mapper = mapper;
        this.equipClient = equipClient;
    }


    @Override
    public ResponseJoueurDTO getJoueurById(Long id) {
        Joueur joueur = repo.findById(id).orElse(null);
        ResponseEquipDTO equipDTO = equipClient.getEquip_ByID(joueur.getId_Equipe());
        joueur.setResponseEquipDTO(equipDTO);
        return mapper.toDto(joueur);
    }

    @Override
    public List<ResponseJoueurDTO> getAllJoueurs() {
        List<Joueur> js = repo.findAll();
        List<ResponseJoueurDTO> dtos = new ArrayList<>();
        for(Joueur j : js){
            ResponseEquipDTO equipDTO = equipClient.getEquip_ByID(j.getId_Equipe());
            j.setResponseEquipDTO(equipDTO);
            dtos.add(mapper.toDto(j));
        }
        return dtos;
    }

    @Override
    public ResponseJoueurDTO addJoueur(RequestJoueurDTO dto) {
        Joueur j = mapper.toEntity(dto);
        return mapper.toDto(repo.save(j));
    }

    @Override
    public ResponseJoueurDTO updateJoueur(Long id, RequestJoueurDTO requestJoueurDTO) {
        Joueur nv = mapper.toEntity(requestJoueurDTO);
        Joueur j = repo.findById(id).orElseThrow();

        if(nv.getNom() !=null) j.setNom(nv.getNom());
        if(nv.getSalaire() !=null) j.setSalaire(nv.getSalaire());
        j.setNbr_buts(nv.getNbr_buts());
        j.setPosition(nv.getPosition());

        Joueur savedJoueur = repo.save(j);
        return mapper.toDto(savedJoueur);
    }

    @Override
    public void deleteJoueur(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Joueur> getAllJoueursByGoals() {
        return repo.findAllByGoals(repo.findAll());
    }

    @Override
    public ResponseJoueurDTO updateJoueurGoals(Long id, int buts) {
        Joueur j = repo.findById(id).orElseThrow();
        j.setNbr_buts(buts+ j.getNbr_buts());
        return mapper.toDto(repo.save(j));
    }

    @Override
    public Joueur getTopScorer() {
        return repo.findTopJoueur(repo.findAll());
    }

    @Override
    public int totalGoals() {
        return repo.totalGoals();
    }
}
