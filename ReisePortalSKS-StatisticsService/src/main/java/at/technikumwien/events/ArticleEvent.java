package at.technikumwien.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor   // optional since default
@Getter
@ToString
public class ArticleEvent {
    private long timestamp;
    private Article article;
}
