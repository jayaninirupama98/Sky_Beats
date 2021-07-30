  <aside class="main-sidebar sidebar-dark bg-white elevation-4">
    <div class="dropdown">
   	<a href="javascript:void(0)" class="brand-link bg-white" data-toggle="dropdown" aria-expanded="true">

        <span class="brand-image d-flex justify-content-center align-items-center text-black font-weight-500" style="width: 38px;height:50px;font-size: 3rem"><img src="images/skybeats1.png" style="width: 98px;height:50px; margin-left: 150px;></span>

        <span class="brand-text font-weight-light ><i>Music</i></span>

      </a>
      <div class="dropdown-menu" style="">
        <a class="dropdown-item manage_account" href="javascript:void(0)" data-id="<?php echo $_SESSION['login_id'] ?>">Manage Account</a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="ajax.php?action=logout">Logout</a>
      </div>
    </div>
    <div class="sidebar">
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column nav-flat" data-widget="treeview" role="menu" data-accordion="false">
          <li class="nav-item dropdown">
            <a href="./index.php?page=home" class="nav-link nav-home">
              <i class="nav-icon fas fa-home text-gradient-white"></i>
              <p>
                Home
              </p>
            </a>
          </li>    
          <li class="nav-item">
            <a href="./index.php?page=music_list" class="nav-link nav-music_list tree-item">           
              <i class="fas fa-music nav-icon text-gradient-white"></i>
              <p>Musics</p>
            </a>
          </li>  
          <li class="nav-item">
                <a href="./index.php?page=playlist" class="nav-link nav-playlist tree-item">
                  <i class="fas fa-list nav-icon  text-gradient-white"></i>
                  <p>Playlist</p>
                </a>
          </li> 
          <?php if($_SESSION['login_type'] == 1): ?>
              <li class="nav-item">
                <a href="./index.php?page=user_list" class="nav-link nav-user_list tree-item">
                  <i class="fas fa-users nav-icon  text-gradient-white"></i>
                  <p>Users</p>
                </a>
          </li> 
          <li class="nav-item">
                <a href="./index.php?page=new_music" class="nav-link nav-new_music tree-item">
                  <i class="fas fa-plus-square nav-icon  text-gradient-white"></i>
                  <p>Add Music</p>
                </a>
          </li> 
          <?php endif; ?>
        </ul>
      </nav>
    </div>
  </aside>
  <script>
  	$(document).ready(function(){
  		var page = '<?php echo isset($_GET['page']) ? $_GET['page'] : 'home' ?>';
  		if($('.nav-link.nav-'+page).length > 0){
  			$('.nav-link.nav-'+page).addClass('active')
          console.log($('.nav-link.nav-'+page).hasClass('tree-item'))
  			if($('.nav-link.nav-'+page).hasClass('tree-item') == true){
          $('.nav-link.nav-'+page).closest('.nav-treeview').siblings('a').addClass('active')
  				$('.nav-link.nav-'+page).closest('.nav-treeview').parent().addClass('menu-open')
  			}
        if($('.nav-link.nav-'+page).hasClass('nav-is-tree') == true){
          $('.nav-link.nav-'+page).parent().addClass('menu-open')
        }

  		}
      $('.manage_account').click(function(){
        uni_modal('Manage Account','manage_user.php?id='+$(this).attr('data-id'))
      })
  	})
  </script>