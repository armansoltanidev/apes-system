package af.apesservice.postservice.controller;

import af.apesservice.postservice.model.dto.request.AddressLookupRequest;
import af.apesservice.postservice.model.dto.request.PostalCodeExistReqDto;
import af.apesservice.postservice.model.dto.request.PostalCodeReqDto;
import af.apesservice.postservice.model.dto.request.PostalCodeVerifyReqDto;
import af.apesservice.postservice.model.dto.response.PostalCodeResDto;
import af.apesservice.postservice.model.dto.response.VerifiedResDto;
import af.apesservice.postservice.service.PostalCodeService;
import af.apesservice.postservice.utils.ApiResponse;
import af.apesservice.postservice.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = { "http://localhost:*", "https://*.yourdomain.com" })
public class PostalCodeController {

    private final PostalCodeService postalCodeService;

    public PostalCodeController(PostalCodeService postalCodeService) {
        this.postalCodeService = postalCodeService;
    }

    @GetMapping("postal-codes")
    public ResponseEntity<ApiResponse<List<PostalCodeResDto>>> findAllPostalCodes() {
        log.info("Request received: findAllPostalCodes");
        List<PostalCodeResDto> data = postalCodeService.findAllPostalCodes();
        log.info("Response sent: findAllPostalCodes - found {} postal codes", data.size());
        return ResponseEntity.ok(ResponseUtil.success("Postal Codes Fetched Successfully", data));
    }

    @PostMapping("postal-code")
    public ResponseEntity<ApiResponse<PostalCodeResDto>> createPostalCode(
            @RequestBody @Valid PostalCodeReqDto postalCodeReqDto) {
        log.info("Request received: createPostalCode - cityId: {}", postalCodeReqDto.getCityId());
        PostalCodeResDto saved = postalCodeService.createPostalCode(postalCodeReqDto);
        log.info("Response sent: createPostalCode - created postal code with id: {}", saved.getId());
        return ResponseEntity.ok(ResponseUtil.success("Postal Code Created Successfully", saved));
    }

    /**
     * Check postal code verified status by postal code
     * 
     * @param postalCodeVerifyReqDto The postal code to check verification status
     * @return VerifiedResDto containing verification status
     */
    @GetMapping("postal-code/check-verify")
    public ResponseEntity<ApiResponse<VerifiedResDto>> checkVerifiedStatus(
            @RequestParam("postalCode") PostalCodeVerifyReqDto postalCodeVerifyReqDto) {
        log.info("Check Verify Status called for postal code: {}", postalCodeVerifyReqDto.getPostalCode());
        VerifiedResDto data = postalCodeService.checkVerifiedStatus(postalCodeVerifyReqDto.getPostalCode());
        return ResponseEntity.ok(ResponseUtil.success("Verification status fetched successfully", data));
    }

    /**
     * Check postal code existence by postal code
     * 
     * @param postalCodeExistReqDto The postal code to check
     * @return boolean indicating if the postal code exists
     */
    @GetMapping("check-postal-code-exist")
    public ResponseEntity<ApiResponse<Boolean>> checkPostalCodeExist(
            @RequestParam("postalCode") PostalCodeExistReqDto postalCodeExistReqDto) {
        log.info("CHECK Postal Code Existence called for postal code: {}", postalCodeExistReqDto.getPostalCode());
        return ResponseEntity.ok(ResponseUtil.success("Postal code existence checked successfully",
                postalCodeService.checkPostalCodeExists(postalCodeExistReqDto.getPostalCode())));
    }

    /**
     * Get a formatted address by postal code
     * 
     * @param addressLookupRequest The request containing postal code and display preferences
     * @return Formatted address information
     */
    @GetMapping("/address")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getFormattedAddress(
            @RequestParam("postalCode") AddressLookupRequest addressLookupRequest,
            @RequestParam(name = "includeCoordinates", required = false, defaultValue = "false") boolean includeCoordinates) {

        Map<String, Object> addressInfo = postalCodeService.getFormattedAddress(
                addressLookupRequest.getPostalCode(),
                includeCoordinates);

        return ResponseEntity.ok(ResponseUtil.success("Address information retrieved successfully", addressInfo));
    }
}
