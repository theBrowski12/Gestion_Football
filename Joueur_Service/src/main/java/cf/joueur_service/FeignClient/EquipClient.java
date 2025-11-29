package cf.joueur_service.FeignClient;

import cf.joueur_service.DTOs.ResponseEquipDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Equipe-service",url = "http://localhost:8081")
public interface EquipClient {
    @GetMapping("/v1/equips/{id}")
    ResponseEquipDTO getEquip_ByID(@PathVariable Long id);


}
