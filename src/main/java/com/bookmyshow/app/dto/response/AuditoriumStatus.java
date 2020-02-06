
package com.bookmyshow.app.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuditoriumStatus {

    private String auditoriumRowId;

    private Integer status;

    private Integer capacity;
}