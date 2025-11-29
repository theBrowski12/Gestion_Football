package cf.joueur_service.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEquipDTO {
    private Long num;
    private String code;
    private String libelle;
    private Long id_joueur;

}
