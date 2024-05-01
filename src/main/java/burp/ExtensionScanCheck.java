package burp;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.scanner.AuditResult;
import burp.api.montoya.scanner.ConsolidationAction;
import burp.api.montoya.scanner.ScanCheck;
import burp.api.montoya.scanner.audit.insertionpoint.AuditInsertionPoint;
import burp.api.montoya.scanner.audit.issues.AuditIssue;
import burp.api.montoya.sitemap.SiteMap;

import java.util.Collections;
import java.util.List;

import static burp.api.montoya.scanner.AuditResult.auditResult;
import static burp.api.montoya.scanner.ConsolidationAction.KEEP_NEW;
import static burp.api.montoya.scanner.audit.issues.AuditIssue.auditIssue;
import static burp.api.montoya.scanner.audit.issues.AuditIssueConfidence.CERTAIN;
import static burp.api.montoya.scanner.audit.issues.AuditIssueSeverity.INFORMATION;

public class ExtensionScanCheck implements ScanCheck
{
    private final SiteMap siteMap;

    private boolean run;

    public ExtensionScanCheck(SiteMap siteMap)
    {
        this.siteMap = siteMap;
        run = false;
    }

    @Override
    public AuditResult activeAudit(HttpRequestResponse httpRequestResponse, AuditInsertionPoint auditInsertionPoint)
    {
        return auditResult();
    }

    @Override
    public AuditResult passiveAudit(HttpRequestResponse httpRequestResponse)
    {
        if (!run)
        {
            run = true;
            List<HttpRequestResponse> requestResponses = siteMap.requestResponses();

            StringBuilder sb = new StringBuilder();

            for (HttpRequestResponse requestResponse : requestResponses)
            {
                sb.append(requestResponse.url()).append("<br>");
            }

            return auditResult(auditIssue(
                    "Site map contents",
                    sb.toString(),
                    null,
                    "http://example.com",
                    INFORMATION,
                    CERTAIN,
                    null,
                    null,
                    INFORMATION,
                    Collections.emptyList()
            ));
        }

        return auditResult();
    }

    @Override
    public ConsolidationAction consolidateIssues(AuditIssue auditIssue, AuditIssue auditIssue1)
    {
        return KEEP_NEW;
    }
}