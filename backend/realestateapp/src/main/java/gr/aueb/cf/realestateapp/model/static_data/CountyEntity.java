package gr.aueb.cf.realestateapp.model.static_data;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "counties")
public class CountyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private RegionEntity region;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "county", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AreaEntity> area = new HashSet<>();

}
