package cf.equipes_service.Service;

import cf.equipes_service.DTOs.RequestEquipeDTO;
import cf.equipes_service.DTOs.ResponseEquipeDTO;

import java.util.List;

public interface EquipService {
    public ResponseEquipeDTO getEquipeById(Long id);
    public List<ResponseEquipeDTO> getAllEquipes();
    public ResponseEquipeDTO addEquipe(RequestEquipeDTO dto);
    public ResponseEquipeDTO updateEquipe(Long id, RequestEquipeDTO requestEquipeDTO);
    public void deleteEquipe(Long id);

}
