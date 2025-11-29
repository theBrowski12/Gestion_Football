package cf.equipes_service.Web;

import cf.equipes_service.DTOs.RequestEquipeDTO;
import cf.equipes_service.DTOs.ResponseEquipeDTO;
import cf.equipes_service.Mappers.EquipeMapper;
import cf.equipes_service.Repository.EquipRepository;
import cf.equipes_service.Service.EquipServiceImpl;
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
                title = "Gestion des Equipes",
                description = "API permettant la gestion complète des équipes",
                version = "1.0.0"
        ),
        servers = @Server(url = "http://localhost:8081")
)
@RestController
@RequestMapping("/v1/equips")
public class API {

    private final EquipServiceImpl service;

    public API(EquipServiceImpl service) {
        this.service = service;
    }

    @Operation(
            summary = "Ajouter une équipe",
            description = "Ajoute une nouvelle équipe dans la base de données.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestEquipeDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Équipe ajoutée avec succès",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEquipeDTO.class)
                            )),
                    @ApiResponse(responseCode = "400", description = "Données invalides"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    @PostMapping
    public ResponseEntity<ResponseEquipeDTO> add(@RequestBody RequestEquipeDTO equipe) {
        return ResponseEntity.ok(service.addEquipe(equipe));
    }

    @Operation(
            summary = "Supprimer une équipe",
            description = "Supprime une équipe selon son ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Équipe supprimée avec succès"),
                    @ApiResponse(responseCode = "404", description = "Équipe introuvable"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipe(@PathVariable Long id) {
        service.deleteEquipe(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Mettre à jour une équipe",
            description = "Modifie une équipe selon l'ID spécifié.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestEquipeDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Équipe mise à jour",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEquipeDTO.class)
                            )),
                    @ApiResponse(responseCode = "404", description = "Équipe introuvable"),
                    @ApiResponse(responseCode = "400", description = "Données invalides"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ResponseEquipeDTO> update(
            @PathVariable Long id,
            @RequestBody RequestEquipeDTO dto
    ) {
        return ResponseEntity.ok(service.updateEquipe(id, dto));
    }

    @Operation(
            summary = "Obtenir une équipe",
            description = "Retourne une équipe selon l'ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Équipe trouvée",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEquipeDTO.class)
                            )),
                    @ApiResponse(responseCode = "404", description = "Équipe introuvable")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseEquipeDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEquipeById(id));
    }

    @Operation(
            summary = "Liste des équipes",
            description = "Retourne toutes les équipes enregistrées.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseEquipeDTO.class)
                            ))
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<ResponseEquipeDTO>> getAll() {
        return ResponseEntity.ok(service.getAllEquipes());
    }
}
