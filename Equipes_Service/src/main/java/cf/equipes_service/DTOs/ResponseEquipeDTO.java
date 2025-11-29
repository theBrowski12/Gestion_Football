package cf.equipes_service.DTOs;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEquipeDTO {
    private Long num;
    private String code;
    private String libelle;
    private Long id_joueur;
}
