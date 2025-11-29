package cf.equipes_service.Mappers;

import cf.equipes_service.DTOs.RequestEquipeDTO;
import cf.equipes_service.DTOs.ResponseEquipeDTO;
import cf.equipes_service.Entity.Equipe;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EquipeMapper {
        public Equipe toEntity(RequestEquipeDTO dto) {
            Equipe entity = new Equipe();
            BeanUtils.copyProperties(dto, entity);
            return entity;
        }

        public ResponseEquipeDTO toDto(Equipe entity) {
            ResponseEquipeDTO dto = new ResponseEquipeDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        }
}
