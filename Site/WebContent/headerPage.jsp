<%@page import="id.base.app.LoginSession"%>
			<ul class="nav navbar-nav quick-actions">
              <!--li class="dropdown divided">
                
                <a class="dropdown-toggle button" data-toggle="dropdown" href="#">
                  <i class="fa fa-bell"></i>
                  <span class="label label-transparent-black">3</span>
                </a>

                <ul class="dropdown-menu wide arrow nopadding bordered">
                  <li><h1>You have <strong>3</strong> new notifications</h1></li>
                  
                  <li>
                    <a href="#">
                      <span class="label label-green"><i class="fa fa-user"></i></span>
                      New user registered.
                      <span class="small">18 mins</span>
                    </a>
                  </li>

                  <li>
                    <a href="#">
                      <span class="label label-red"><i class="fa fa-power-off"></i></span>
                      Server down.
                      <span class="small">27 mins</span>
                    </a>
                  </li>

                  <li>
                    <a href="#">
                      <span class="label label-orange"><i class="fa fa-plus"></i></span>
                      New order.
                      <span class="small">36 mins</span>
                    </a>
                  </li>

                  <li>
                    <a href="#">
                      <span class="label label-cyan"><i class="fa fa-power-off"></i></span>
                      Server restared.
                      <span class="small">45 mins</span>
                    </a>
                  </li>

                  <li>
                    <a href="#">
                      <span class="label label-amethyst"><i class="fa fa-power-off"></i></span>
                      Server started.
                      <span class="small">50 mins</span>
                    </a>
                  </li>

                   <li><a href="#">Check all notifications <i class="fa fa-angle-right"></i></a></li>
                </ul>

              </li -->

              <li class="dropdown divided user" id="current-user" style="min-width:200px">
                <a class="dropdown-toggle options" data-toggle="dropdown" href="#" style="float:right">
                	<span class="fa-stack fa-lg">
			          <i class="fa fa-circle fa-stack-2x"></i>
			          <i class="fa fa-key fa-stack-1x fa-inverse"></i>
			        </span>
			 		<% LoginSession user = (LoginSession) session.getAttribute("user");%><%=user.getName() %> <i class="fa fa-caret-down"></i>
                </a>
                
                <ul class="dropdown-menu arrow settings">                 
                  <li>
                    <a href="#"><i class="fa fa-user"></i> Profile</a>
                  </li>

                  <li class="divider"></li>
                  <li><p><i class="fa fa-cogs"></i> Settings </p></li>
				  	<li id="company">
						<a href="/Web/page/settings/company/showList">
                        	<i class="fa fa-caret-right"></i> Penggilingan Padi
                        </a>
                  	</li>
                  	<li id="thirdParty">
						<a href="/Web/page/settings/thirdParty/showList">
                        	<i class="fa fa-caret-right"></i> Pihak Ketiga
                        </a>
                  	</li>
                  	<li id="product">
						<a href="/Web/page/settings/product/showList">
                        	<i class="fa fa-caret-right"></i> Item
                        </a>
                  	</li>
                  	<li id="warehouse">
						<a href="/Web/page/settings/warehouse/showList">
                        	<i class="fa fa-caret-right"></i> Gudang
                        </a>
                  	</li>
                  	<li id="machinery">
						<a href="/Web/page/settings/machinery/showList">
                        	<i class="fa fa-caret-right"></i> Mesin
                        </a>
                  	</li>
                  	<li id="transporter">
                        <a href="/Web/page/settings/transporter/showList">
                          <i class="fa fa-caret-right"></i> Pengangkut
                        </a>
                   	</li>
                   	<li id="masterFee">
                        <a href="/Web/page/settings/fee/showList">
                          <i class="fa fa-caret-right"></i> Biaya - Biaya
                        </a>
                   	</li>
                   	<li id="companyLookup">
                        <a href="/Web/page/settings/lookup/showList">
                          <i class="fa fa-caret-right"></i> Referensi
                        </a>
                   	</li>
                  <li class="divider"></li>
                  <li>
                  	<a href="#" id="logoutBtn"><i class="fa fa-power-off"></i> Logout</a>
						<script>
			            	$('#logoutBtn').click(function(){
			                	window.location.href = '/Web/page/login/out';
							});
						</script>
                  </li>
                </ul>
              </li>
            </ul>