package cf.equipes_service.Entity;

import cf.equipes_service.DTOs.ResponseJoueurDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipe {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long num;
    private String code;
    private String libelle;
    @Transient
    private Long id_joueur;
}
