package af.apesservice.postservice.enums;


import lombok.Getter;

@Getter
public enum ZoneType {
    RESIDENTIAL("مناطق مسکونی شامل خانه‌ها، آپارتمان‌ها و سکونت‌گاه‌های شخصی"),
    COMMERCIAL("مناطق تجاری شامل فروشگاه‌ها، بازارها، دفاتر تجاری و مراکز خرید"),
    ADMINISTRATIVE("مناطق اداری شامل ساختمان‌های دولتی و مراکز خدمات عمومی"),
    INDUSTRIAL("مناطق صنعتی شامل کارخانه‌ها، کارگاه‌ها و نواحی تولیدی"),
    EDUCATIONAL("مناطق آموزشی شامل مدارس، دانشگاه‌ها و مؤسسات آموزشی");

    private final String description;

    ZoneType(String description) {
        this.description = description;
    }

}