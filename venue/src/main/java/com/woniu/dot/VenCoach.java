package com.woniu.dot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenCoach implements Serializable {

    private List<VenDotCoachs> coachs;
}
