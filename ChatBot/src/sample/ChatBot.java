package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class ChatBot extends Bot {

    private Random random;
    private Pattern pattern;

    final private String[] simplePhrases = {
            "Нет ничего ценнее слов, сказанных к месту и ко времени.",
            "Порой молчание может сказать больше, нежели уйма слов.",
            "Перед тем как писать/говорить всегда лучше подумать.",
            "Вежливая и грамотная речь говорит о величии души.",
            "Приятно когда текст без орфографических ошибок.",
            "Многословие есть признак неупорядоченного ума.",
            "Слова могут ранить, но могут и исцелять.",
            "Записывая слова, мы удваиваем их силу.",
            "Кто ясно мыслит, тот ясно излагает.",
            "Боюсь Вы что-то не договариваете."
    };

    final private String[] illusionAnswer = {
            "Вопрос непростой, прошу тайм-аут на раздумья.",
            "Не уверен, что располагаю такой информацией.",
            "Может лучше поговорим о чём-то другом?",
            "Простите, но это очень личный вопрос.",
            "Не уверен, что Вам понравится ответ.",
            "Поверьте, я сам хотел бы это знать.",
            "Вы действительно хотите это знать?",
            "Уверен, Вы уже догадались сами.",
            "Зачем Вам такая информация?",
            "Давайте сохраним интригу?"
    };

    final private Map<String, String> keys = new HashMap<String, String>() {{
        // hello
        put("хай", "hello");
        put("привет", "hello");
        put("здорово", "hello");
        put("здравствуй", "hello");
        // who
        put("кто\\s.*ты", "who");
        put("ты\\s.*кто", "who");
        // name
        put("как\\s.*зовут", "name");
        put("как\\s.*имя", "name");
        put("есть\\s.*имя", "name");
        put("какое\\s.*имя", "name");
        // howareyou
        put("как\\s.*дела", "howareyou");
        put("как\\s.*жизнь", "howareyou");
        // whatdoyoudoing
        put("зачем\\s.*тут", "whatdoyoudoing");
        put("зачем\\s.*здесь", "whatdoyoudoing");
        put("что\\s.*делаешь", "whatdoyoudoing");
        put("чем\\s.*занимаешься", "whatdoyoudoing");
        // whatdoyoulike
        put("что\\s.*нравится", "whatdoyoulike");
        put("что\\s.*любишь", "whatdoyoulike");
        // iamfeelling
        put("кажется", "iamfeelling");
        put("чувствую", "iamfeelling");
        put("испытываю", "iamfeelling");
        // yes
        put("^да", "yes");
        put("согласен", "yes");
        // whattime
        put("который\\s.*час", "whattime");
        put("сколько\\s.*время", "whattime");
        // bye
        put("прощай", "bye");
        put("увидимся", "bye");
        put("до\\s.*свидания", "bye");
    }};

    final Map<String, String> answersByKeys = new HashMap<String, String>() {{
        put("hello", "Здравствуйте, рад Вас видеть.");
        put("who", "Я обычный чат-бот.");
        put("name", "Меня зовут Боб.");
        put("howareyou", "Спасибо, что интересуетесь. У меня всё хорошо.");
        put("whatdoyoudoing", "Я пробую общаться с людьми.");
        put("whatdoyoulike", "Мне нравиться думать что я не просто программа.");
        put("iamfeelling", "Как давно это началось? Расскажите чуть подробнее.");
        put("yes", "Согласие есть продукт при полном непротивлении сторон.");
        put("bye", "До свидания. Надеюсь, ещё увидимся.");
    }};

    public ChatBot() {
        super();
        random = new Random();
    }

    @Override
    public String say(String message) {
        boolean stupidAnswer = false;
        if(stupidAnswer) {
            String say = message.trim().endsWith("?")?illusionAnswer[random.nextInt(illusionAnswer.length)]:simplePhrases[random.nextInt(simplePhrases.length)];
            return say;
        }
        /*Split("[ {,|.}?]+")) - съедаем все знаки препинания и т.п.
        * toLowerCase - опускаем все в нижний регистр для удобства работы
        * join - опять объединяем в*/
        else {
            String temp = String.join(" ", message.toLowerCase().split("[ {,|.}?]+"));
            for (Map.Entry<String, String> i : keys.entrySet()) {
                pattern = Pattern.compile(i.getKey());
                /*Есть ли такой ключ*/
                if (pattern.matcher(temp).find())
                    if (i.getValue().equals("whattime")) return new Date().toString();
                    else return answersByKeys.get(i.getValue());
            }
        }
        return null;
    }

    @Override
    public String loadName() throws FileNotFoundException {
        File file = new File("information.csv");
        Scanner scanner = new Scanner(file);
        String name = null;
        while(scanner.hasNextLine()) {
            name = scanner.nextLine();
        }
        return name;
    }

    @Override
    public void addHistory(String message) {

    }

    @Override
    public void loadHistory(String message) {

    }

}
