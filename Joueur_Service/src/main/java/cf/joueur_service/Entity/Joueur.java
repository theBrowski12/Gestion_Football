package cf.joueur_service.Entity;

import cf.joueur_service.DTOs.ResponseEquipDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Joueur {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String position;
    private Double salaire;
    private int nbr_buts;
    private Long id_Equipe;
    @Transient
    private ResponseEquipDTO responseEquipDTO;
}
