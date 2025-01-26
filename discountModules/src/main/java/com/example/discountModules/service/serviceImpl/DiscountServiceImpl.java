package com.example.discountModules.service.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.discountModules.dto.request.ApplyDiscountRequest;
import com.example.discountModules.dto.request.DiscountCreateRequest;
import com.example.discountModules.dto.request.DiscountUpdateRequest;
import com.example.discountModules.dto.response.ApplyDiscountResponse;
import com.example.discountModules.dto.response.BaseResponse;
import com.example.discountModules.dto.response.DiscountResponse;
import com.example.discountModules.dto.response.ItemResponse;
import com.example.discountModules.entity.Discount.CampaignDiscount;
import com.example.discountModules.entity.Discount.CategoryPercentageDiscount;
import com.example.discountModules.entity.Discount.Discount;
import com.example.discountModules.entity.Discount.FixedAmountDiscount;
import com.example.discountModules.entity.Discount.PercentageDiscount;
import com.example.discountModules.entity.Discount.PointsDiscount;
import com.example.discountModules.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {

    private static List<Discount> discounts = new ArrayList<>();

    static {
        // ! Fixed Amount Discount
        discounts.add(new FixedAmountDiscount(
                UUID.randomUUID().toString(),
                "Fixed Amount Discount",
                "ลดราคา 50 บาท",
                50.0));

        // ! Percentage Discount
        discounts.add(new PercentageDiscount(
                UUID.randomUUID().toString(),
                "Percentage Discount",
                "ลดราคา 20%",
                20.0));

        // ! Category Percentage Discount
        discounts.add(new CategoryPercentageDiscount(
                UUID.randomUUID().toString(),
                "Category Discount",
                "ลดราคาสำหรับหมวด เสื้อผ้า 15%",
                "เสื้อผ้า",
                15.0));

        // ! Points Discount
        discounts.add(new PointsDiscount(
                UUID.randomUUID().toString(),
                "Points Discount",
                "ลด 1 บาทต่อ 1 แต้ม สูงสุด 20%",
                20.0));

        // ! Campaign Discount
        discounts.add(new CampaignDiscount(
                UUID.randomUUID().toString(),
                "Campaign Discount",
                "ลดราคาเมื่อซื้อครบทุก 500 บาท รับส่วนลด 100 บาท",
                500,
                100.0));

    }

    @Override
    public List<DiscountResponse> getAllDiscounts() {
        List<DiscountResponse> discountResponses = new ArrayList<>();

        for (Discount discount : discounts) {
            DiscountResponse discountResponse = new DiscountResponse();
            discountResponse.setId(discount.getId());
            discountResponse.setName(discount.getName());
            discountResponse.setDescription(discount.getDescription());
            discountResponse.setType(discount.getType());

            if (discount instanceof CampaignDiscount) {
                CampaignDiscount campaignDiscount = (CampaignDiscount) discount;
                discountResponse.setMinProductCount(campaignDiscount.getMinProductCount());
                discountResponse.setDiscountAmount(campaignDiscount.getDiscountAmount());
            } else if (discount instanceof CategoryPercentageDiscount) {
                CategoryPercentageDiscount categoryDiscount = (CategoryPercentageDiscount) discount;
                discountResponse.setCategory(categoryDiscount.getCategory());
                discountResponse.setDiscountPercentage(categoryDiscount.getDiscountPercentage());
            } else if (discount instanceof FixedAmountDiscount) {
                FixedAmountDiscount fixedDiscount = (FixedAmountDiscount) discount;
                discountResponse.setDiscountAmount(fixedDiscount.getDiscountAmount());
            } else if (discount instanceof PercentageDiscount) {
                PercentageDiscount percentageDiscount = (PercentageDiscount) discount;
                discountResponse.setDiscountPercentage(percentageDiscount.getDiscountPercentage());
            } else if (discount instanceof PointsDiscount) {
                PointsDiscount pointsDiscount = (PointsDiscount) discount;
                discountResponse.setDiscountPercentage(pointsDiscount.getDiscountPercentage());
            }

            discountResponses.add(discountResponse);
        }

        return discountResponses;
    }

    @Override
    public BaseResponse createDiscount(DiscountCreateRequest request) {
        BaseResponse response = new BaseResponse();
        Discount discount = discounts.stream().filter(d -> d.getName().equals(request.getName())).findFirst()
                .orElse(null);

        if (discount != null) {
            response.setCode("400");
            response.setStatus("fail");
            Map<String, String> details = Map.of("name", "Discount name already exists");
            response.setDetails(details);
            return response;
        }

        switch (request.getType()) {
            case "fixedAmount":
                if (request.getDiscountAmount() <= 0) {
                    response.setCode("400");
                    response.setStatus("fail");
                    Map<String, String> details = Map.of("discountAmount",
                            "Discount amount must be greater than 0");
                    response.setDetails(details);
                    return response;
                }

                discount = new FixedAmountDiscount(
                        UUID.randomUUID().toString(),
                        request.getName(),
                        request.getDescription(),
                        request.getDiscountAmount());
                break;
            case "percentage":
                if (request.getDiscountPercentage() <= 0) {
                    response.setCode("400");
                    response.setStatus("fail");
                    Map<String, String> details = Map.of("discountPercentage",
                            "Discount percentage must be greater than 0");
                    response.setDetails(details);
                    return response;
                }

                discount = new PercentageDiscount(
                        UUID.randomUUID().toString(),
                        request.getName(),
                        request.getDescription(),
                        request.getDiscountPercentage());
                break;
            case "category":
                if (request.getDiscountPercentage() <= 0) {
                    response.setCode("400");
                    response.setStatus("fail");
                    Map<String, String> details = Map.of("discountPercentage",
                            "Discount percentage must be greater than 0");
                    response.setDetails(details);
                    return response;
                }

                discount = new CategoryPercentageDiscount(
                        UUID.randomUUID().toString(),
                        request.getName(),
                        request.getDescription(),
                        request.getCategory(),
                        request.getDiscountPercentage());
                break;
            case "points":
                if (request.getDiscountPercentage() <= 0) {
                    response.setCode("400");
                    response.setStatus("fail");
                    Map<String, String> details = Map.of("discountPercentage",
                            "Discount percentage must be greater than 0");
                    response.setDetails(details);
                    return response;
                }

                discount = new PointsDiscount(
                        UUID.randomUUID().toString(),
                        request.getName(),
                        request.getDescription(),
                        request.getDiscountPercentage());
                break;
            case "campaign":
                if (request.getMinProductCount() <= 0 || request.getDiscountAmount() <= 0) {
                    response.setCode("400");
                    response.setStatus("fail");
                    Map<String, String> details = Map.of("minProductCount",
                            "Minimum product count and discount amount must be greater than 0");
                    response.setDetails(details);
                    return response;
                }

                discount = new CampaignDiscount(
                        UUID.randomUUID().toString(),
                        request.getName(),
                        request.getDescription(),
                        request.getMinProductCount(),
                        request.getDiscountAmount());
                break;
            default:
                break;
        }

        if (discount != null) {
            discounts.add(discount);
        }

        response.setCode("200");
        response.setStatus("success");
        response.setDetails(null);

        return response;
    }

    @Override
    public BaseResponse updateDiscount(String id, DiscountUpdateRequest request) {
        BaseResponse response = new BaseResponse();
        Discount discount = discounts.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);

        if (discount != null) {
            if (!request.getType().equals(discount.getType())) {
                response.setCode("400");
                response.setStatus("fail");
                Map<String, String> details = Map.of("type", "Type cannot be changed");
                response.setDetails(details);
                return response;
            }

            discount.setName(request.getName());
            discount.setDescription(request.getDescription());

            if (discount instanceof CampaignDiscount) {
                CampaignDiscount campaignDiscount = (CampaignDiscount) discount;
                campaignDiscount.setMinProductCount(request.getMinProductCount());
                campaignDiscount.setDiscountAmount(request.getDiscountAmount());
            } else if (discount instanceof CategoryPercentageDiscount) {
                CategoryPercentageDiscount categoryDiscount = (CategoryPercentageDiscount) discount;
                categoryDiscount.setCategory(request.getCategory());
                categoryDiscount.setDiscountPercentage(request.getDiscountPercentage());
            } else if (discount instanceof FixedAmountDiscount) {
                FixedAmountDiscount fixedDiscount = (FixedAmountDiscount) discount;
                fixedDiscount.setDiscountAmount(request.getDiscountAmount());
            } else if (discount instanceof PercentageDiscount) {
                PercentageDiscount percentageDiscount = (PercentageDiscount) discount;
                percentageDiscount.setDiscountPercentage(request.getDiscountPercentage());
            } else if (discount instanceof PointsDiscount) {
                PointsDiscount pointsDiscount = (PointsDiscount) discount;
                pointsDiscount.setDiscountPercentage(request.getDiscountPercentage());
            }

            response.setCode("200");
            response.setStatus("success");
            response.setDetails(null);
        } else {
            response.setCode("404");
            response.setStatus("fail");
            Map<String, String> details = Map.of("detail", "Discount not found");
            response.setDetails(details);
        }

        return response;
    }

    @Override
    public BaseResponse deleteDiscount(String id) {
        BaseResponse response = new BaseResponse();
        Discount discount = discounts.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);

        if (discount != null) {
            discounts.remove(discount);
            response.setCode("200");
            response.setStatus("success");
            Map<String, String> details = Map.of("detail", "Discount has been deleted successfully");
            response.setDetails(details);
        } else {
            response.setCode("404");
            response.setStatus("fail");
            Map<String, String> details = Map.of("detail", "Discount not found");
            response.setDetails(details);
        }

        return response;
    }

    @Override
    public ApplyDiscountResponse applyDiscount(ApplyDiscountRequest request) {
        double totalPrice = 0.0;
        double discountAmount = 0.0;
        double finalPrice = 0.0;
        List<ItemResponse> items = request.getItems();
        List<DiscountResponse> appliedDiscounts = new ArrayList<>();

        for (ItemResponse item : items) {
            totalPrice += item.getPrice() * item.getQuantity();
        }

        finalPrice = totalPrice;

        List<DiscountCreateRequest> discounts = new ArrayList<>(Collections.nCopies(3, null));
        for (DiscountCreateRequest discountRequest : request.getDiscount()) {
            if ((discountRequest.getType().equals("fixedAmount")
                    || discountRequest.getType().equals("percentage")) && discounts.get(0) == null) {
                discounts.set(0, discountRequest);
            } else if ((discountRequest.getType().equals("category")
                    || discountRequest.getType().equals("points")) && discounts.get(1) == null) {
                discounts.set(1, discountRequest);
            } else if (discountRequest.getType().equals("campaign") && discounts.get(2) == null) {
                discounts.set(2, discountRequest);
            }
        }

        // ประมวลผลส่วนลด
        for (DiscountCreateRequest discountRequest : discounts) {
            if (discountRequest.getType().equals("fixedAmount")) {
                // ใช้ส่วนลดแบบ Fixed Amount
                discountAmount += discountRequest.getDiscountAmount();
                finalPrice -= discountRequest.getDiscountAmount();

                DiscountResponse discountResponse = new DiscountResponse();
                discountResponse.setId(discountRequest.getId());
                discountResponse.setName(discountRequest.getName());
                discountResponse.setDescription(discountRequest.getDescription());
                discountResponse.setType(discountRequest.getType());
                discountResponse.setDiscountAmount(discountRequest.getDiscountAmount());
                appliedDiscounts.add(discountResponse);

            } else if (discountRequest.getType().equals("percentage")) {
                // ใช้ส่วนลดแบบ Percentage
                double percentageDiscount = totalPrice * (discountRequest.getDiscountPercentage() / 100.0);
                discountAmount += percentageDiscount;
                finalPrice -= percentageDiscount;

                DiscountResponse discountResponse = new DiscountResponse();
                discountResponse.setId(discountRequest.getId());
                discountResponse.setName(discountRequest.getName());
                discountResponse.setDescription(discountRequest.getDescription());
                discountResponse.setType(discountRequest.getType());
                discountResponse.setDiscountPercentage(discountRequest.getDiscountPercentage());
                appliedDiscounts.add(discountResponse);

            } else if (discountRequest.getType().equals("category")) {
                // ใช้ส่วนลดเฉพาะหมวดหมู่
                for (ItemResponse item : items) {
                    if (item.getCategory().equals(discountRequest.getCategory())) {
                        double categoryDiscount = item.getPrice() * item.getQuantity()
                                * (discountRequest.getDiscountPercentage() / 100.0);
                        discountAmount += categoryDiscount;
                        finalPrice -= categoryDiscount;
                    }
                }

                DiscountResponse discountResponse = new DiscountResponse();
                discountResponse.setId(discountRequest.getId());
                discountResponse.setName(discountRequest.getName());
                discountResponse.setDescription(discountRequest.getDescription());
                discountResponse.setType(discountRequest.getType());
                discountResponse.setCategory(discountRequest.getCategory());
                discountResponse.setDiscountPercentage(discountRequest.getDiscountPercentage());
                appliedDiscounts.add(discountResponse);

            } else if (discountRequest.getType().equals("points")) {
                // ใช้ส่วนลดแบบ Points
                double pointsDiscount = finalPrice * (discountRequest.getDiscountPercentage() / 100.0);
                double totalPointsDiscount = 0;
                if (request.getPoint() < pointsDiscount) {
                    totalPointsDiscount = request.getPoint();
                } else {
                    totalPointsDiscount = pointsDiscount;
                }

                discountAmount += totalPointsDiscount;
                finalPrice -= totalPointsDiscount;

                DiscountResponse discountResponse = new DiscountResponse();
                discountResponse.setId(discountRequest.getId());
                discountResponse.setName(discountRequest.getName());
                discountResponse.setDescription(discountRequest.getDescription());
                discountResponse.setType(discountRequest.getType());
                discountResponse.setDiscountPercentage(discountRequest.getDiscountPercentage());
                appliedDiscounts.add(discountResponse);

            } else if (discountRequest.getType().equals("campaign")) {
                // ใช้ส่วนลดแบบ Campaign (ลดตามยอดซื้อ)
                int eligibleDiscounts = (int) (finalPrice / discountRequest.getMinProductCount());
                double campaignDiscount = eligibleDiscounts * discountRequest.getDiscountAmount();
                discountAmount += campaignDiscount;
                finalPrice -= campaignDiscount;

                DiscountResponse discountResponse = new DiscountResponse();
                discountResponse.setId(discountRequest.getId());
                discountResponse.setName(discountRequest.getName());
                discountResponse.setDescription(discountRequest.getDescription());
                discountResponse.setType(discountRequest.getType());
                discountResponse.setMinProductCount(discountRequest.getMinProductCount());
                discountResponse.setDiscountAmount(discountRequest.getDiscountAmount());
                appliedDiscounts.add(discountResponse);
            }
        }

        if (finalPrice < 0) {
            finalPrice = 0;
        }

        // สร้าง Response
        ApplyDiscountResponse response = new ApplyDiscountResponse();
        response.setPrice(totalPrice);
        response.setDiscountAmount(discountAmount);
        response.setFinalPrice(finalPrice);
        response.setItems(items);
        response.setAppliedDiscounts(appliedDiscounts);

        return response;
    }

}
