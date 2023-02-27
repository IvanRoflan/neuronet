package test.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 *
 * @author vaganovdv
 */
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(String.format("%-30s %5s %s", "Старт теста", "==>", result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(String.format("%-30s %5s %s", "Тест выполнено успешно", "==>", result.getMethod().getMethodName()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(String.format("%-30s %5s %s", "Тест выполнен с ошибками", "==>", result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(String.format("%-30s %5s %s", "Тест пропущено", "==>", result.getMethod().getMethodName()));
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println(String.format("\n%-30s %5s %s", "Запуск теста", "==>", context.getName()));
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("\n----------------------- Резульаты тестирования --------------------------");
        System.out.println(String.format("%-30s %s", "Успешо выполнено тестов:", "[" + context.getPassedTests().size() + "]"));
        if (context.getFailedTests().size() > 0) {
            System.out.println(String.format("%-30s %s", "Ошибки выполнения тестов:", "[" + context.getFailedTests().size() + "] <=== !"));
        } else {
            System.out.println(String.format("%-30s %s", "Ошибки выполнения тестов:", "[" + context.getFailedTests().size() + "]"));
        }
        System.out.println(String.format("%-30s %s", "Пропущено тестов:", "[" + context.getSkippedTests().size() + "]"));
        System.out.println("-------------------------------------------------------------------------\n");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

}
