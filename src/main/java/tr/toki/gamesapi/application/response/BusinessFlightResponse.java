/*
 * adesso AG
 * Adessoplatz 1
 * 44269 Dortmund
 * Tel.: +49 (0) 231 7000-7000
 * Fax.: +49 (0) 231 7000-1000
 * Mail: info@adesso.de
 * Web : www.adesso.de
 *
 * Copyright (c) 2019 adesso AG, all rights reserved
 */
package tr.toki.gamesapi.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import tr.toki.gamesapi.application.mapper.BusinessFlight;

/**
 * Business Flight Response class
 */
@Data
public class BusinessFlightResponse {

    @JsonProperty
    private BusinessFlight[] data;
}   
