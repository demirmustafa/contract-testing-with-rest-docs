package io.github.demirmustafa.producer.model;

import lombok.*;

/**
 * @author Mustafa Demir <mustafa.demir@sahabt.com>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private Long id;
    private String name;
    private String description;

}
