[#ftl output_format="HTML"]
[#-- Need to explicitly set the output format here, so that auto escaping is enabled by default (as of Freemarker 2.3.24) --]
[#-- This will mitigate the risk of XSS attacks from the default login page. Such a precaution is not needed in other templates, when Magnolia takes care of rendering. --]
<div class="form">
    <div class="text">
        [#if content.title?has_content]<h1>${content.title}</h1>[/#if]
    </div>
    [#if "anonymous" == ctx.user.name || cmsfn.editMode]
        [#assign requiredLabel = content.requiredLabel!i18n['template.login.required']!]
        [#assign requiredCharacter = content.requiredCharacter!i18n['template.login.required.character']!]
        [#if content.text?has_content]<div class="text">${content.text}</div>[/#if]
        <br>
        <div class="form-wrapper">
            <form id="loginForm" method="post" enctype="application/x-www-form-urlencoded">
                [#if content.realmName?has_content]
                    <input type="hidden" name="mgnlRealm" value="${content.realmName!'public'}"/>
                [/#if]

                <input type="hidden" name="mgnlModelExecutionUUID" value="${content.@uuid}"/>
                [#if content.targetPage?has_content]
                    <input type="hidden" name="mgnlReturnTo" value="${cmsfn.link("website", content.targetPage)!}"/>
                [/#if]

                ${ctx.response.setHeader("Cache-Control", "no-cache")}
                <input type="hidden" name="csrf" value="${ctx.getAttribute('csrf')!''}" />

                <p class="required"><span>${requiredCharacter}</span> ${requiredLabel}</p>

                <fieldset>
                    <div class="form-row">
                        <label for="username"><span>${i18n['template.login.username.label']} <dfn
                                        title="required">${requiredCharacter}</dfn></span></label>
                        <input required="required" type="text" id="username" name="mgnlUserId" value="" maxlength="50"/>
                    </div>
                    <div class="form-row">
                        <label class="" for="mgnlUserPSWD"><span>${i18n['template.login.password.label']} <dfn
                                        title="required">${requiredCharacter}</dfn></span></label>
                        <input required="required" type="password" name="mgnlUserPSWD" id="mgnlUserPSWD" value=""/>
                    </div>

                    [#if content.registrationPage?has_content]<a href="${cmsfn.link("website", content.registrationPage)!}">${i18n['template.login.registrationPageLink']}</a> | [/#if]
                    [#if content.forgottenPasswordPage?has_content]<a href="${cmsfn.link("website", content.forgottenPasswordPage!)!}">${i18n['template.login.forgottenPasswordPageLink']}</a>[/#if]

                    <div class="button-wrapper">
                        <fieldset class="buttons">
                            <input type="submit" class="submit" accesskey="s"
                                   value="${i18n['template.login.submit.label']}"/>
                        </fieldset>
                    </div>

                </fieldset>
            </form>
        </div><!-- end form-wrapper -->
    [/#if]
    [#if "anonymous" != ctx.user.name || cmsfn.editMode]
        <div class="text">${i18n.get('template.login.welcome', [ctx.user.name])}</div>
        <a href="${cmsfn.link(cmsfn.page(content))!}?mgnlLogout=true">${i18n['template.login.submit.logout.label']}</a>
    [/#if]

    [#if ctx.mgnlLoginError.loginException?has_content]
        <div class="loginError">
            <p>${ctx.mgnlLoginError.loginException.message}</p>
        </div>
    [/#if]
</div>