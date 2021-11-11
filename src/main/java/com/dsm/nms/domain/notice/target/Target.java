package com.dsm.nms.domain.notice.target;

import com.dsm.nms.domain.notice.noticetarget.NoticeTarget;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Target {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TargetTag targetTag;

    @OneToMany(mappedBy = "target")
    private List<NoticeTarget> notices;

}