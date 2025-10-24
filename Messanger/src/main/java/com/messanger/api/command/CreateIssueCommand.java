package com.messanger.api.command;

import com.messanger.application.interfaces.IIssueService;
import com.messanger.application.interfaces.INotificationService;
import com.messanger.domain.model.IssuePostResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateIssueCommand implements Command {
    private final IIssueService issueService;
    private final INotificationService notificationService;

    @Override
    public String name() {
        return "/create-issue";
    }

    @Override
    public String description() {
        return "Creates a new issue: /create-issue --project-id <project-id> <summary> <description>";
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 4 || !args[0].equalsIgnoreCase("--project-id")) {
            System.out.println("❌ Usage: /createIssue --project-id <project-id> <summary> <description>");
            return;
        }

        String projectId = args[1];
        String summary = args[2];
        String description = String.join(" ", java.util.Arrays.copyOfRange(args, 3, args.length));

        try {
            IssuePostResponse issue = issueService.execute(summary, description, projectId);
            System.out.printf("✅ Issue '%s' '%s' created successfully.%n", issue.getIdReadable(), issue.getSummary());
        } catch (Exception e) {
            System.out.println("❌ Failed to create issue: " + e.getMessage());
        }
    }

}
