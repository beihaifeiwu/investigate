package com.freetmp.investigate.crsh

import org.apache.commons.io.output.WriterOutputStream
import org.crsh.command.BaseCommand
import org.crsh.command.InvocationContext
import org.springframework.util.StringUtils

public abstract class AbstractCrashCommand extends BaseCommand {
/*
    AbstractCrashCommand(Class<? extends AbstractCrashCommand> aClass) {
        aClass.metaClass.invokeMethod = { Object object, String name, Object args ->
            def m = aClass.metaClass.getMetaMethod(name, args)
            def method = findMethod(aClass, name);
            def annotation = method.getAnnotation(CaptureStdout.class)
            if (annotation != null) {
                def so = System.out
                synchronized (AbstractCrashCommand.class) {
                    System.out = new PrintStream(new WriterOutputStream(out))
                }
                try {
                    return m.invoke(delegate, args)
                } finally {                    synchronized (AbstractCrashCommand.class) {
                        System.out = so
                    }
                }
            } else {
                return m.invoke(delegate, args)
            }

        }
    }*/

    public static Boolean readBoolean(InvocationContext context, String msg) throws IOException, InterruptedException {
        String read = readInCandidates(context, String.format("%s[y/N]: ", msg), "y", "yes", "n", "no");
        return read.equalsIgnoreCase("y") || read.equalsIgnoreCase("yes");
    }

    public static Integer readInt(InvocationContext context, String msg) throws IOException, InterruptedException {
        String read = "1";
        boolean found = false;
        while (!found) {
            read = context.readLine(msg, true);
            if (!StringUtils.isEmpty(read)) {
                try {
                    Integer.parseInt(read);
                    found = true;
                } catch (NumberFormatException e) {
                    found = false;
                }
            }
        }
        return Integer.parseInt(read);
    }

    public static String readInCandidates(InvocationContext context, String msg, String... candidates) throws IOException, InterruptedException {
        boolean found = false;
        String read = candidates[0];
        while (!found) {
            read = context.readLine(msg, true);
            if (StringUtils.isEmpty(read)) read = candidates[0];
            for (String candidate : candidates) {
                if (!StringUtils.isEmpty(read) && read.equalsIgnoreCase(candidate)) {
                    found = true;
                    read = candidate;
                    break;
                }
            }
        }
        return read;
    }

    public static Object runWithStdoutCapture(InvocationContext context, Closure closure) {
        synchronized (System.out) {
            PrintStream out = System.out;
            try {
                System.out = new PrintStream(new WriterOutputStream(context.getWriter()))
                closure.call()
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out = out
            }
        }
    }
}