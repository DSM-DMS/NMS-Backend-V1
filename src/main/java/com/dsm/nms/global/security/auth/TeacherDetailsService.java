package com.dsm.nms.global.security.auth;

import com.dsm.nms.domain.teacher.facade.TeacherFacade;
import com.dsm.nms.domain.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeacherDetailsService implements UserDetailsService {

    private final TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return new TeacherDetails(teacherRepository.findByUsernameOrEmail(id, id).orElseThrow());
    }

}
