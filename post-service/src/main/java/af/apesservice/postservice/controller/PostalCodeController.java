package af.apesservice.postservice.controller;


import af.apesservice.postservice.model.reqModel.CommercialPostalCodeReqDto;
import af.apesservice.postservice.model.reqModel.PostalCodeReqDto;
import af.apesservice.postservice.model.reqModel.PostalCodeVerifyRequest;
import af.apesservice.postservice.model.resModel.PostalCodeResDto;
import af.apesservice.postservice.model.resModel.VerifiedResDto;
import af.apesservice.postservice.service.CommercialPostalCodeService;
import af.apesservice.postservice.service.PostalCodeService;
import af.apesservice.postservice.service.UrbanPostalCodeService;
import af.apesservice.postservice.utils.ApiResponse;
import af.apesservice.postservice.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("api/v1")
public class PostalCodeController {

    private final PostalCodeService postalCodeService;
    private final UrbanPostalCodeService urbanPostalCodeService;

    private final CommercialPostalCodeService commercialPostalCodeService;


    public PostalCodeController(PostalCodeService postalCodeService, UrbanPostalCodeService urbanPostalCodeService, CommercialPostalCodeService commercialPostalCodeService) {
        this.postalCodeService = postalCodeService;
        this.urbanPostalCodeService = urbanPostalCodeService;
        this.commercialPostalCodeService = commercialPostalCodeService;
    }


    @GetMapping("postal-codes")
    public ResponseEntity<ApiResponse<List<PostalCodeResDto>>>
    findAllPostalCodes() {
        List<PostalCodeResDto> data = postalCodeService.findAllPostalCodes();
        log.info("Postal Codes Fetched Successfully");
        return ResponseEntity.ok(ResponseUtil.success("Postal Codes Fetched Successfully", data));
    }


    @GetMapping("urban-postal-codes")
    public ResponseEntity<ApiResponse<List<PostalCodeResDto>>> findAllUrbanPostalCodes() {
        List<PostalCodeResDto> data = urbanPostalCodeService.findAllUrbanPostalCode();
        return ResponseEntity.ok(ResponseUtil.success("Urban Postal Codes Fetched Successfully", data));
    }


    @PostMapping("urban-postal-codes")
    public ResponseEntity<ApiResponse<PostalCodeResDto>> createUrbanPostalCode(@Valid @RequestBody PostalCodeReqDto postalCodeReqDto) {
        // ZoneType is automatically set to RESIDENTIAL in the UrbanPostalCode entity
        PostalCodeResDto data = urbanPostalCodeService.createPostalCode(postalCodeReqDto);
        return ResponseEntity.ok(ResponseUtil.success("Urban Postal Code Created Successfully", data));
    }

    @PostMapping("commercial-postal-codes")
    public ResponseEntity<ApiResponse<PostalCodeResDto>> createCommercialPostalCode(@Valid @RequestBody CommercialPostalCodeReqDto commercialPostalCodeReqDto) {
        // ZoneType is automatically set to COMMERCIAL in the CommercialPostalCode entity
        PostalCodeResDto data = commercialPostalCodeService.createPostalCode(commercialPostalCodeReqDto);
        return ResponseEntity.ok(ResponseUtil.success("Commercial Postal Code Created Successfully", data));
    }


    @GetMapping("commercial-postal-codes")
    public ResponseEntity<ApiResponse<List<PostalCodeResDto>>> findAllCommercialPostalCodes() {
        List<PostalCodeResDto> data = commercialPostalCodeService.findAllCommercialPostalCodes();

        return ResponseEntity.ok(ResponseUtil.success("Commercial Postal Codes Fetched Successfully", data));

    }


    @PostMapping("urban-postal-codes/verify")
    public ResponseEntity<ApiResponse<VerifiedResDto>> checkVerifiedStatus(@RequestBody PostalCodeVerifyRequest request) {
        VerifiedResDto data = urbanPostalCodeService.checkVerifiedStatus(request.getPostalCode());
        return ResponseEntity.ok(ResponseUtil.success("Verification status fetched successfully", data));
    }
    
    
}
