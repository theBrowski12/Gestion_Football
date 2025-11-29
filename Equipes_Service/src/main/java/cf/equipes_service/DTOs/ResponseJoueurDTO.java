package cf.equipes_service.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseJoueurDTO {
    private Long id;
    private String nom;
    private String position;
    private String salaire;
    private int nbr_buts;
}
