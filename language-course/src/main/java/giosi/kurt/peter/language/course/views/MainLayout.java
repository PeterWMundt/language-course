package giosi.kurt.peter.language.course.views;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.server.menu.MenuConfiguration;
import com.vaadin.flow.server.menu.MenuEntry;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * The main layout is a top-level placeholder for other views.
 */
@SuppressWarnings("serial")
@Layout
@AnonymousAllowed
public class MainLayout extends AppLayout {
	
	private static final Logger	LOGGER	= LoggerFactory.getLogger(MainLayout.class);
	
	private H1					viewTitle;
	
	public MainLayout() {
		LOGGER.debug("constructor");
		
		this.setPrimarySection(Section.DRAWER);
		this.addDrawerContent();
		this.addHeaderContent();
	}
	
	private void addHeaderContent() {
		LOGGER.debug("addHeaderContent");
		
		DrawerToggle toggle = new DrawerToggle();
		toggle.setAriaLabel(this.getTranslation("menu.toggle"));
		
		this.viewTitle = new H1();
		this.viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
		
		this.addToNavbar(true, toggle, this.viewTitle);
	}
	
	private void addDrawerContent() {
		LOGGER.debug("addDrawerContent");
		
		Span appName = new Span(this.getTranslation("spanish.course"));
		appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
		Header header = new Header(appName);
		
		Scroller scroller = new Scroller(this.createNavigation());
		
		this.addToDrawer(header, scroller, this.createFooter());
	}
	
	private SideNav createNavigation() {
		LOGGER.debug("createNavigation");
		
		SideNav nav = new SideNav();
		
		List<MenuEntry> menuEntries = MenuConfiguration.getMenuEntries();
		menuEntries.forEach(entry -> {
			if (entry.icon() != null) {
				nav.addItem(new SideNavItem(entry.title(), entry.path(), new SvgIcon(entry.icon())));
			} else {
				nav.addItem(new SideNavItem(entry.title(), entry.path()));
			}
		});
		
		return nav;
	}
	
	private Footer createFooter() {
		LOGGER.debug("createFooter");
		
		Footer layout = new Footer();
		
		return layout;
	}
	
	@Override
	protected void afterNavigation() {
		LOGGER.debug("afterNavigation");
		
		super.afterNavigation();
		this.viewTitle.setText(this.getCurrentPageTitle());
	}
	
	private String getCurrentPageTitle() {
		LOGGER.debug("getCurrentPageTitle");
		
		return MenuConfiguration.getPageHeader(this.getContent()).orElse("");
	}
}
