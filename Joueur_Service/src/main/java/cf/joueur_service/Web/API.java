package cf.joueur_service.Web;


import cf.joueur_service.DTOs.RequestJoueurDTO;
import cf.joueur_service.DTOs.ResponseJoueurDTO;
import cf.joueur_service.Entity.Joueur;
import cf.joueur_service.Mappers.JoueurMapper;
import cf.joueur_service.Repository.JoueurRepository;
import cf.joueur_service.Service.JoueurService;
import cf.joueur_service.Service.JoueurServiceImpl;
import feign.Response;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@OpenAPIDefinition(
        info = @Info(
                title= "Gestion des Joueurs",
                description= "Offre toutes les méthodes pour gérer les Joueurs",
                version= "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8082/"
        )
)
@RestController
@RequestMapping("/v1/joueurs")
public class API {

    private final JoueurServiceImpl service;
    private final JoueurMapper mapper;
    private final JoueurRepository repo;

    public API(JoueurServiceImpl service, JoueurMapper mapper, JoueurRepository repo) {
        this.service = service;
        this.mapper = mapper;
        this.repo = repo;
    }

    // ------------------------------------------
    // 1) ADD JOUEUR
    // ------------------------------------------
    @Operation(
            summary = "Ajouter un joueur",
            description = "Ajoute un joueur dans la base de données.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestJoueurDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Joueur ajouté avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseJoueurDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Données invalides"),
                    @ApiResponse(responseCode = "500", description = "Erreur serveur")
            }
    )
    @PostMapping
    public ResponseEntity<ResponseJoueurDTO> add(@RequestBody RequestJoueurDTO joueur) {
        ResponseJoueurDTO dto = service.addJoueur(joueur);
        return ResponseEntity.ok(dto);
    }

    // ------------------------------------------
    // 2) DELETE JOUEUR
    // ------------------------------------------
    @Operation(
            summary = "Supprimer un joueur",
            description = "Supprime un joueur à partir de son ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Joueur supprimé avec succès"),
                    @ApiResponse(responseCode = "404", description = "Joueur non trouvé"),
                    @ApiResponse(responseCode = "500", description = "Erreur serveur")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipe(@PathVariable Long id) {
        service.deleteJoueur(id);
        return ResponseEntity.ok().build();
    }

    // ------------------------------------------
    // 3) UPDATE JOUEUR
    // ------------------------------------------
    @Operation(
            summary = "Mettre à jour un joueur",
            description = "Met à jour un joueur existant.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestJoueurDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Joueur mis à jour",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseJoueurDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Joueur non trouvé"),
                    @ApiResponse(responseCode = "500", description = "Erreur serveur")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ResponseJoueurDTO> update(
            @PathVariable Long id,
            @RequestBody RequestJoueurDTO requestJoueurDTO) {

        ResponseJoueurDTO responseJoueurDTO = service.updateJoueur(id, requestJoueurDTO);
        return ResponseEntity.ok(responseJoueurDTO);
    }


    // ------------------------------------------
    // 4) GET BY ID
    // ------------------------------------------
    @Operation(
            summary = "Récupérer un joueur par ID",
            description = "Retourne un joueur selon son ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Joueur trouvé",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseJoueurDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Joueur non trouvé")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseJoueurDTO> getById(@PathVariable("id") Long id) {
        ResponseJoueurDTO dto = service.getJoueurById(id);
        return ResponseEntity.ok(dto);
    }

    // ------------------------------------------
    // 5) GET ALL JOUEURS
    // ------------------------------------------
    @Operation(
            summary = "Lister tous les joueurs",
            description = "Retourne la liste complète des joueurs.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste des joueurs",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseJoueurDTO.class)))
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<ResponseJoueurDTO>> getAll() {
        List<ResponseJoueurDTO> responseJoueurDTOS = service.getAllJoueurs();
        return ResponseEntity.ok(responseJoueurDTOS);
    }


    // ------------------------------------------
    // 6) GET JOUEURS ORDERED BY GOALS
    // ------------------------------------------
    @Operation(
            summary = "Joueurs triés par nombre de buts",
            description = "Retourne la liste des joueurs triés par nombre de buts (décroissant).",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste triée",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Joueur.class)))
            }
    )
    @GetMapping("/goals")
    public ResponseEntity<List<Joueur>> getByGoals() {
        List<Joueur> joueurs = service.getAllJoueursByGoals();
        return ResponseEntity.ok(joueurs);
    }


    // ------------------------------------------
    // 7) TOTAL GOALS
    // ------------------------------------------
    @Operation(
            summary = "Total des buts marqués",
            description = "Retourne la somme totale des buts marqués par tous les joueurs.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Total calculé",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Integer.class)))
            }
    )
    @GetMapping("/total")
    public ResponseEntity<Integer> getTotalGoals() {
        int t = service.totalGoals();
        return ResponseEntity.ok(t);
    }
    // ------------------------------------------
    // 8) UPDATE GOALS
    // ------------------------------------------
    @Operation(
            summary = "Mis A jour des buts marqués",
            description = "Mis A jour des buts marqués.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "New goals updated",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Integer.class)))
            }
    )
    @PatchMapping("/updateJoueur/{id}")
    public ResponseEntity<ResponseJoueurDTO> updateJoueurGoals(@PathVariable("id") Long id, int buts) {
        Joueur j = repo.findById(id).orElseThrow();
        ResponseJoueurDTO dto = mapper.toDto(j);
        dto = service.updateJoueurGoals(id, buts);
        return ResponseEntity.ok(dto);
    }
}

