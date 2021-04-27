package com.abo.shopping.service;

import com.abo.shopping.converter.DTOConverter;
import com.abo.shopping.model.Shop;
import com.abo.shopping.repository.ReportRepository;
import com.abo.shopping.repository.ShopRepository;
import com.abo.shoppingclient.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    public List<ShopDTO> getAll() {
        List<Shop> shops = shopRepository.findAll();
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        List<Shop> shops = shopRepository
                .findAllByUserIdentifier(userIdentifier);
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO) {
        List<Shop> shops = shopRepository
                .findAllByDateGreaterThan(shopDTO.getDate());
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(long ProductId) {
        Optional<Shop> shop = shopRepository.findById(ProductId);
        if (shop.isPresent()) {
            return DTOConverter.convert(shop.get());
        }
        return null;
    }

    public ShopDTO save(ShopDTO shopDTO, String key) {
        UserDTO userDTO = userService
                .getUserByIdentification(shopDTO.getUserIdentifier(), key);

        validateProducts(shopDTO.getItems());
        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(x -> x.getPrice())
                .reduce((float) 0, Float::sum));

        Shop shop = DTOConverter.convert(shopDTO);
        shop.setDate(new Date());

        shop = shopRepository.save(shop);
        return DTOConverter.convert(shop);
    }

    public List<ShopDTO> getShopsByFilter(
            Date startDate,
            Date endDate,
            Float mininumValue) {
        List<Shop> shops =
                reportRepository
                        .getShopByFilters(startDate, endDate, mininumValue);
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(
            Date startDate,
            Date endDate) {
        return reportRepository
                .getReportByDate(startDate, endDate);
    }

    private boolean validateProducts(List<ItemDTO> items) {
        for (ItemDTO  item : items) {
            ProductDTO productDTO = productService
                    .getProductByIdentifier(
                            item.getProductIdentifier());
            if (productDTO == null) {
                return false;
            }
            item.setPrice(productDTO.getPrice());
        }
        return true;
    }

}
