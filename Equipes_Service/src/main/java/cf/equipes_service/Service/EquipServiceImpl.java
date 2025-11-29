package cf.equipes_service.Service;

import cf.equipes_service.DTOs.RequestEquipeDTO;
import cf.equipes_service.DTOs.ResponseEquipeDTO;
import cf.equipes_service.Entity.Equipe;
import cf.equipes_service.Mappers.EquipeMapper;
import cf.equipes_service.Repository.EquipRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipServiceImpl implements EquipService {
    private final EquipRepository repo;
    private final EquipeMapper mapper;

    public EquipServiceImpl(EquipRepository repo, EquipeMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public ResponseEquipeDTO getEquipeById(Long id) {
        Equipe equipe =  repo.findById(id).get();
        return mapper.toDto(equipe);
    }

    @Override
    public List<ResponseEquipeDTO> getAllEquipes() {
        List<Equipe> equipes = repo.findAll();
        List<ResponseEquipeDTO> dtos = new ArrayList<>();
        for(Equipe equipe : equipes){
            dtos.add(mapper.toDto(equipe));
        }
        return dtos;
    }

    @Override
    public ResponseEquipeDTO addEquipe(RequestEquipeDTO dto) {
        Equipe equipe = mapper.toEntity(dto);
        return mapper.toDto(repo.save(equipe));
    }

    @Override
    public ResponseEquipeDTO updateEquipe(Long id, RequestEquipeDTO requestEquipeDTO) {
        Equipe nv_equip = mapper.toEntity(requestEquipeDTO);
        Equipe equip = repo.findById(id).orElseThrow();

        if(nv_equip.getCode() !=null) equip.setCode(nv_equip.getCode());
        if(nv_equip.getLibelle() !=null) equip.setLibelle(nv_equip.getLibelle());

        Equipe savedEquip = repo.save(equip);
        return mapper.toDto(savedEquip);
    }


    @Override
    public void deleteEquipe(Long id) {
        repo.deleteById(id);
    }
}
