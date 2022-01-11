package fr.bred.example.interview.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * City criteria pattern for search.
 */
@Getter
@Setter
@NoArgsConstructor
public class CityCriteria {

    private String namePattern;

    private String zipCodePattern;

    public CityCriteria(String namePattern, String zipCodePattern) {
        this.namePattern = transformToLikeExpression(namePattern);
        this.zipCodePattern = transformToLikeExpression(zipCodePattern);
    }

    /**
     * Returns the pattern string transform to upperCase and wildcard to %.
     *
     * @param pattern pattern
     * @return patter transformed
     */
    private String transformToLikeExpression(String pattern) {
        if (Objects.isNull(pattern)) {
            return null;
        }
        return pattern.toUpperCase().replace("*", "%");
    }
}
