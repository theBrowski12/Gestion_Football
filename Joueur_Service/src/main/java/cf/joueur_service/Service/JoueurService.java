package cf.joueur_service.Service;


import cf.joueur_service.DTOs.RequestJoueurDTO;
import cf.joueur_service.DTOs.ResponseJoueurDTO;
import cf.joueur_service.Entity.Joueur;

import java.util.List;

public interface JoueurService {
    public ResponseJoueurDTO getJoueurById(Long id);
    public List<ResponseJoueurDTO> getAllJoueurs();
    public ResponseJoueurDTO addJoueur(RequestJoueurDTO dto);
    public ResponseJoueurDTO updateJoueur(Long id, RequestJoueurDTO requestJoueurDTO);
    public void deleteJoueur(Long id);
    //lister les joueur ordre decroissante
    public List<Joueur> getAllJoueursByGoals();
    public ResponseJoueurDTO updateJoueurGoals(Long id, int buts);
    public List<Joueur> findTopJoueurs();
    public int totalGoals();

}
