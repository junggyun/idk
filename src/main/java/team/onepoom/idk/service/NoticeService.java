package team.onepoom.idk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.onepoom.idk.domain.notice.Notice;
import team.onepoom.idk.common.exception.NoticeNotFoundException;
import team.onepoom.idk.domain.notice.dto.AllNoticeResponse;
import team.onepoom.idk.domain.notice.dto.DetailNoticeResponse;
import team.onepoom.idk.domain.notice.dto.FiveNoticeResponse;
import team.onepoom.idk.repository.NoticeRepository;

@Service
@Transactional(readOnly = true)
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Transactional
    public void create(Notice notice) {
        noticeRepository.save(notice);
    }

    @Transactional
    public void update(Long id, Notice notice) {
        Notice updateNotice = noticeRepository.findById(id).orElseThrow(NoticeNotFoundException::new);
        updateNotice.update(notice.getTitle(), notice.getContent());
    }

    @Transactional
    public void delete(long id) {
        Notice deleteNotice = noticeRepository.findById(id).orElseThrow(NoticeNotFoundException::new);
        noticeRepository.delete(deleteNotice);
    }

    @Transactional
    public DetailNoticeResponse showDetailNotice(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(NoticeNotFoundException::new);
        notice.incrementViews();
        return DetailNoticeResponse.from(notice);
    }

    public Page<AllNoticeResponse> showAllNotice(Pageable pageable) {
        Page<Notice> noticePage = noticeRepository.findAll(pageable);
        return noticePage.map(AllNoticeResponse::from);
    }

    public Page<FiveNoticeResponse> getFiveNotices(Pageable pageable) {
        Page<Notice> noticePage = noticeRepository.findAllByOrderByCreatedAtDesc(pageable);
        return noticePage.map(FiveNoticeResponse::from);
    }
}