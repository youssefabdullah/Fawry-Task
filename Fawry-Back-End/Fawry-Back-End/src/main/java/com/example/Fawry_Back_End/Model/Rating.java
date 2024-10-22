package com.example.Fawry_Back_End.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
    @JsonProperty("Source")
    private String source;
    @JsonProperty("Value")
    private String value;
}
