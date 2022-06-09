package cl.minisecurityserver.securityservertest.util;

import cl.minisecurityserver.securityservertest.dao.entity.Privilege;
import cl.minisecurityserver.securityservertest.dao.entity.pk.PrivilegePK;
import cl.minisecurityserver.securityservertest.dto.PrivilegeRequestDto;
import cl.minisecurityserver.securityservertest.dto.PrivilegeResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class PrivilegeMapper {
  public static Privilege build(PrivilegeRequestDto requestDto) {
    return Privilege.builder()
        .id(
            PrivilegePK.builder()
                .profileId(requestDto.getProfileId())
                .roleId(requestDto.getRoleId())
                .build())
        .key(requestDto.getKey())
        .description(requestDto.getDescription())
        .build();
  }

  public static PrivilegeResponseDto build(Privilege privilege) {

    return PrivilegeResponseDto.builder()
        .id(privilege.getId().getId())
        .profileId(privilege.getId().getProfileId())
        .roleId(privilege.getId().getRoleId())
        .key(privilege.getKey())
        .description(privilege.getDescription())
        .createdAt(privilege.getCreatedAt())
        .updatedAt(privilege.getUpdatedAt())
        .build();
  }

  public static Page<PrivilegeResponseDto> build(Page<Privilege> privileges) {

    return new PageImpl<>(
        privileges.stream().map(PrivilegeMapper::build).collect(Collectors.toList()),
        privileges.getPageable(),
        privileges.getTotalElements());
  }

  public static List<PrivilegeResponseDto> build(List<Privilege> privileges) {

    return privileges.stream().map(PrivilegeMapper::build).collect(Collectors.toList());
  }
}
