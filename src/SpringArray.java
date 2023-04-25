import java.util.Stack;

public class SpringArray {
    public static Spring equivalentSpring(String springExpr) {
        Stack<Spring> stack = new Stack<>();
        for (int i = 0; i < springExpr.length(); i++) {
            char c = springExpr.charAt(i);
            if (c == '{') {
                stack.push(new Spring());
            } else if (c == '[') {
                stack.push(new Spring());
            } else if (c == '}') {
                Spring s = stack.pop();
                if (!stack.empty() && stack.peek() != null) {
                    stack.add(s);
                } else {
                    stack.push(s);
                }
            } else if (c == ']') {
                Spring s = stack.pop();
                if (!stack.empty() && stack.peek() != null) {
                    stack.add(s);
                } else {
                    stack.push(s);
                }
            }
        }

        return stack.pop();
    }

    public static Spring equivalentSpring(String springExpr, Spring[] springs) {
        Stack<Spring> stack = new Stack<>();
        for (int i = 0; i < springExpr.length(); i++) {
            char c = springExpr.charAt(i);
            if (c == '{') {
                stack.push(springs[0]);
            } else if (c == '[') {
                stack.push(springs[0]);
            } else if (c == '}') {
                Spring s = stack.pop();
                if (!stack.empty() && stack.peek() != null) {
                    stack.add(s);
                } else {
                    stack.push(s);
                }
            } else if (c == ']') {
                Spring s = stack.pop();
                if (!stack.empty() && stack.peek() != null) {
                    stack.add(s);
                } else {
                    stack.push(s);
                }
            }
        }
        return stack.pop();
    }
}
