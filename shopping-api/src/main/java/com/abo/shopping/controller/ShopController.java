package com.abo.shopping.controller;

import com.abo.shopping.service.ShopService;
import com.abo.shoppingclient.dto.ShopDTO;
import com.abo.shoppingclient.dto.ShopReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/shopping")
    public List<ShopDTO> getShops() {
        List<ShopDTO> produtos = shopService.getAll();
        return produtos;
    }

    @GetMapping("/shopping/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShops(
            @PathVariable String userIdentifier) {
        List<ShopDTO> produtos =
                shopService.getByUser(userIdentifier);
        return produtos;
    }

    @GetMapping("/shopping/shopByDate")
    public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO) {
        List<ShopDTO> produtos = shopService.getByDate(shopDTO);
        return produtos;
    }

    @GetMapping("/shopping/{id}")
    public ShopDTO findById(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @PostMapping("/shopping")
    public ShopDTO newShop(@RequestHeader(name = "key", required=true) String key, @Valid @RequestBody ShopDTO shopDTO) {
        return shopService.save(shopDTO, key);
    }

    @GetMapping("/shopping/search")
    public List<ShopDTO> getShopsByFilter(
            @RequestParam(name = "startDate", required=true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
            @RequestParam(name = "endDate", required=false)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate,
            @RequestParam(name = "mininumValue", required=false)
                    Float mininumValue) {
        return shopService.getShopsByFilter(startDate, endDate, mininumValue);
    }

    @GetMapping("/shopping/report")
    public ShopReportDTO getReportByDate(
            @RequestParam(name = "startDate", required=true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
            @RequestParam(name = "endDate", required=true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate) {
        return shopService.getReportByDate(startDate, endDate);
    }
}
