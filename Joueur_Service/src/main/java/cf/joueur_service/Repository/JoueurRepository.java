package cf.joueur_service.Repository;

import cf.joueur_service.Entity.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JoueurRepository extends JpaRepository<Joueur, Long> {
    @Query("SELECT j FROM Joueur j ORDER BY j.nbr_buts DESC")
    public List<Joueur> findAllByGoals(List<Joueur> joueurs);

    @Query("SELECT j FROM Joueur j ORDER BY j.nbr_buts DESC")
    public Joueur findTopJoueur(List<Joueur> joueurs);

    @Query("SELECT sum(j.nbr_buts) FROM Joueur j ")
    public int totalGoals();

}
