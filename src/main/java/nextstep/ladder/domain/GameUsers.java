package nextstep.ladder.domain;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class GameUsers {

    private static final String INVALID_USER_COUNT = "사다리 게임에 참여하는 사람은 최소 2명 이상이어야합니다.";
    private List<User> users;

    public GameUsers(String[] userNames) {
        validUserCount(userNames);
        AtomicInteger position = new AtomicInteger();
        this.users = Arrays.stream(userNames)
            .map(userName -> new User(userName, position.getAndIncrement()))
            .collect(Collectors.toList());
    }

    private void validUserCount(String[] userNames) {
        if (userNames == null || userNames.length < 2) {
            throw new IllegalArgumentException(INVALID_USER_COUNT);
        }
    }

    public int getUserCount() {
        return users.size();
    }

    public List<String> getUserNames() {
        return users.stream()
            .map(User::getName)
            .collect(Collectors.toList());
    }

    public List<Integer> getUserPositions() {
        return users.stream()
            .map(User::getCurrentPosition)
            .collect(Collectors.toList());
    }

    public void run(Ladder ladder) {
        users.stream()
            .forEach(ladder::move);
    }
}
