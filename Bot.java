import java.util.*;
import java.util.regex.*;

public class Bot {

    //QWITHOUTQM питання,яке бот не розумє і дає заготовлену відповідь
    final String[] QWITHOUTQM = {
            "Ви все правильно написали?",
            "Перевірте написане, щось не вірно",
            "Таких даних немає"};

    //QWITHQM питання, на яке бот не має написаної відповіді
    final String[] QWITHQM = {
            "Я цього не знаю",
            "Мені це невідомо"};
    final Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {{
        // привітання
        put("привіт", "hello");
        put("здоров", "hello");
        put("здравствуйте", "hello");
        put("добрий день", "hello");
        put("вітаю", "hello");
        put("доброго ранку", "hello");
        put("доброго вечора", "hello");
        // прощання
        put("пока", "bye");
        put("до побачення", "bye");
        // адреса
        put("де я", "address");
        put("адреса", "address");
        put("яка адреса", "address");
        put("де інститут", "address");
        // час
        put("час", "time");
        put("котра\\s.*година", "time");
        put("скільки\\s.*часу", "time");
        put("скільки\\s.*годин", "time");
        // назва інституту
        put("як\\s.*називається", "name");
        put("що це", "name");
        put("назва", "name");
        put("яка\\s.*назва", "name");
        // туалет
        put("туалет", "wc");
        put("вбиральня", "wc");
        // корпус
        put("кортус", "frame");
        // floors of frame 1
        put("ground floor 1", "fof1");
        put("second floor 1", "fof1");
        put("third floor 1", "fof1");
        put("fourth floor1", "fof1");
        // floors of frame 2
        put("ground floor 2", "fof2");
        put("second floor2", "fof2");
        put("third floor2", "fof2");
        put("fourth floor 2", "fof2");
        put("5th floor 2", "fof2");
        put("6th floor 2", "fof2");
        // floors of frame 3
        put("ground floor 3", "fof3");
        put("second floor 3", "fof3");
        put("third floor 3", "fof3");
        put("fourth floor 3", "fof3");
        // floors of frame 4
        put("ground floor 4", "fof4");
        put("second floor 4", "fof4");
        put("third floor 4", "fof4");
        put("fourth floor 4", "fof4");
        put("5th floor 4", "fof4");
        put("6th floor 4", "fof4");
        put("7th floor 4", "fof4");
        put("8th floor 4", "fof4");
        put("9th floor 4", "fof4");
        // help (куди звернутися по допомогу)
        put("немає студентського", "help");
        put("заблукав", "help");
        put("знайти кабінет", "help");
        put("зверніться до сторожа", "help");

    }};
    final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {{
        put("hello", "Чим можу допомогти?");
        put("bye", "До побачення.");
        put("address", "Ви в вулиця Шевченка, 95, Чернігів");
        put("name", "Чернігівська Політехніка");
        put("frame", "Всього 4 корпуса, напишіть в яких хочете");
        put("wc", "Перший корпус зліва від головного входу");
        put("fof1", "Вам потрібно зайти з головного входу");
        put("fof2", "Вам потрібно вам потрібно зайти в 4 корпус та через ньго пройти в 2 корпус ");
        put("fof3", "Вам потрібно пройти в парк та зайти в 4 поверхову будівлю");
        put("fof4", "Вам потрібно зайти в девятиповерхівку");
        put("help", "Вам можуть допомогти при вході в будь який корпус");
    }};

    Pattern pattern;
    Random random;
    Date date;

    public Bot() {
        random = new Random();
        date = new Date();
    }
    public String sayInReturn(String msg, boolean ai) {
        String say = (msg.trim().endsWith("?"))? //перевірка повідомлень з ? для відправлення ботом повідомлень
                QWITHQM[random.nextInt(QWITHQM.length)]: //рандомна відповідь,якщо бот не розуміє питання
                QWITHOUTQM[random.nextInt(QWITHOUTQM.length)];

        if (ai) {
            String message = String.join(" ", msg.toLowerCase().split(" ,.?"));
            //переводимо всі ведені букви в нижній регістр та прибираємо знаки ( ,.?),
            for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()) { //перебір шаблонів для аналізу
                pattern = Pattern.compile(o.getKey()); //Компіляція шаблону
                if (pattern.matcher(message).find()) //програма перевіряє,чи ведений текст користувачем є в шаблонах програми
                    if (o.getValue().equals("time")) return date.toString(); //для запиттаня часу
                    else return ANSWERS_BY_PATTERNS.get(o.getValue());
            }
        }
        return say;

    }
}