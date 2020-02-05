
package com.bookmyshow.app.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BlockSeatResponse {

    private String message;

    private boolean isBlocked;
}