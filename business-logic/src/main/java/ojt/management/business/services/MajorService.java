package ojt.management.business.services;

import ojt.management.common.exceptions.MajorNameAlreadyExistedException;
import ojt.management.common.exceptions.MajorNotExistedException;
import ojt.management.common.payload.request.MajorRequest;
import ojt.management.data.entities.Major;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface MajorService {
    Major getMajorById(Long id) throws MajorNotExistedException;

    Page<Major> searchMajor(Specification<Major> specification, Pageable pageable) ;

    Major updateMajor(Long id, MajorRequest majorRequest) throws MajorNotExistedException, MajorNameAlreadyExistedException;

    boolean deleteMajor(Long id) throws MajorNotExistedException;

    Major createMajor(String name) throws MajorNameAlreadyExistedException;
}
