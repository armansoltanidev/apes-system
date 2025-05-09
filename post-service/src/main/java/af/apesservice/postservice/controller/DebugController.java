package af.apesservice.postservice.controller;

import af.apesservice.postservice.model.entity.PostalCode;
import af.apesservice.postservice.model.dto.request.PostalCodeReqDto;
import af.apesservice.postservice.mapper.PostalCodeMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/debug")
public class DebugController {

    private final PostalCodeMapper postalCodeMapper;

    public DebugController(PostalCodeMapper postalCodeMapper) {
        this.postalCodeMapper = postalCodeMapper;
    }

    @PostMapping("test-mapping")
    public Map<String, Object> testMapping(@RequestBody PostalCodeReqDto reqDto) {
        Map<String, Object> result = new HashMap<>();
        
        // Show what was received
        result.put("receivedDto", reqDto);
        
        // Show what would be mapped
        PostalCode entity = postalCodeMapper.toEntity(reqDto);
        result.put("mappedEntity", entity);
        
        return result;
    }
} 