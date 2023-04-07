package at.technikumwien.dtos;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class StatisticsDTO {
    String title;
    Long clicks;


    public static List<StatisticsDTO> generateOutputList(List<String> titles, List<Long> clicks){
        List<StatisticsDTO> statisticsDTOList = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            statisticsDTOList.add(new StatisticsDTO(titles.get(i), clicks.get(i)));
        }
        return statisticsDTOList;
    }
}
