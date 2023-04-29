import java.util.Stack;
public class SpringArray {
    public static Spring equivalentSpring(String springExpr) {
        Stack<Spring> stack = new Stack<>();
        for (char c : springExpr.toCharArray()) {
            if (c == '[' || c == '{') {
                stack.push(new Spring());
            } else if (c == ']') {
                Spring currentSpring = stack.pop();
                Spring previousSpring = stack.pop();
                stack.push(previousSpring.inParallel(currentSpring));
            } else if (c == '}') {
                Spring currentSpring = stack.pop();
                Spring previousSpring = stack.pop();
                stack.push(previousSpring.inSeries(currentSpring));
            }
        }
        return stack.pop();
    }

    public static Spring equivalentSpring(String springExpr, Spring[] springs) {
        Stack<Spring> stack = new Stack<>();
        for (char c : springExpr.toCharArray()) {
            if (c == '[' || c == '{') {
                stack.push(springs[0]);
            } else if (c == ']') {
                Spring currentSpring = stack.pop();
                Spring previousSpring = stack.pop();
                stack.push(previousSpring.inParallel(currentSpring));
            } else if (c == '}') {
                Spring currentSpring = stack.pop();
                Spring previousSpring = stack.pop();
                stack.push(previousSpring.inSeries(currentSpring));
            }
        }
        return stack.pop();
    }
}
