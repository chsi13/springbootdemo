package org.chsi.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.Serializable;

/**
 * Created by chsi on 20/01/2018.
 */

@Data
@RequiredArgsConstructor(staticName = "of")
@ToString
@EqualsAndHashCode
public class Author implements Serializable {

    @NonNull
    private final String firstname;

    @NonNull
    private final String name;

}
