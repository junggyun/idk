package team.onepoom.idk.common.exception;

public class AnswerForbiddenException extends ForbiddenException {

    public AnswerForbiddenException(long id) {
        super("접근 권한이 없습니다. id: " + id);
    }
}