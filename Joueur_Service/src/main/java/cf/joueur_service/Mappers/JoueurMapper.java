package cf.joueur_service.Mappers;

import cf.joueur_service.DTOs.RequestJoueurDTO;
import cf.joueur_service.DTOs.ResponseJoueurDTO;
import cf.joueur_service.Entity.Joueur;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class JoueurMapper {
        public Joueur toEntity(RequestJoueurDTO dto) {
        Joueur entity = new Joueur();
            BeanUtils.copyProperties(dto, entity);
            return entity;
        }

        public ResponseJoueurDTO toDto(Joueur entity) {
            ResponseJoueurDTO dto = new ResponseJoueurDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        }
}
