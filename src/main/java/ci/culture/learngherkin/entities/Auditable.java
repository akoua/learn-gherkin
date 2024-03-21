package ci.culture.learngherkin.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(value = EntityListeners.class)
public class Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedBy
    @Column(name = "created_by")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected String createdBy;

    @CreatedDate
    @Column(name = "created_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected Date createdDate = new Date();

    @LastModifiedDate
    @Column(name = "last_modified_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected Date lastModifiedDate = new Date();

    @org.springframework.data.annotation.LastModifiedBy
    @Column(name = "Last_modified_by")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected String lastModifiedBy;
}
