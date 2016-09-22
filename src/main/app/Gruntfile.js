module.exports = function(grunt) {
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		
		distFolder: 'dist',

		vendorScriptFiles: [
		'vendor/angular/angular.min.js',
		'vendor/angular-locale-pt-br/angular-locale_pt-br.js',
		'vendor/angular-utils-pagination/dirPagination.js',
		'vendor/angular-route/angular-route.min.js',
		'vendor/angular-resource/angular-resource.min.js',
		'vendor/angular-input-masks/angular-input-masks-standalone.min.js',
		'vendor/angular-mask/dist/ngMask.min.js',
		'vendor/sweetalert/dist/sweetalert.min.js',
		'vendor/underscore/underscore-min.js',
		'js/<%= pkg.name %>.min.js'
		],

		personalScriptFiles: [
		'public/js/main.js',
		'public/js/controllers/MainController.js',
		'public/js/controllers/ItemController.js',
		'public/js/controllers/ItemsController.js',
		'public/js/controllers/BuyController.js',

		'public/js/directives/SideMenuDirective.js',
		'public/js/directives/ThOrderableDirective.js',
		'public/js/directives/CartDirective.js',
		'public/js/directives/CartItemDirective.js',
		'public/js/directives/SellableItemSelectionDirective.js',

		'public/js/filters/ItemFilter.js',

		'public/js/services/ItemService.js',
		'public/js/services/BuyService.js',
		'public/js/services/CartService.js',
		'public/js/services/CartItemService.js',
		],

		stylesheetFiles:[
		'vendor/bootstrap/dist/css/bootstrap.min.css',
		'vendor/bootstrap/dist/css/bootstrap-theme.min.css',
		'vendor/sweetalert/dist/sweetalert.css'
		],

		jshint: {
			options: {
				reporter: require('jshint-stylish')
			},
			build: ['Gruntfile.js', 'public/js/**/*.js']
		},

		clean: {
			dist: {
				src: '<%= distFolder %>'
			},

			stylesheets: {
				src: 'public/css/*.css'
			}
		},

		copy: {
			dist:  {
				cwd: 'public',
				src: ['**/*', '!vendor/**/*', '!js/**/*.js','!**/less/*.less', '<%= vendorScriptFiles %>', '<%= stylesheetFiles %>'],
				dest: '<%= distFolder %>',
				expand: true
			}
		},


		less: {
			build: {
				src: 'public/less/<%= pkg.name %>.less',
				dest: 'public/css/<%= pkg.name %>.css'
			}
		},

		cssmin: {
			options: {
				banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd hh-MM-ss") %> */\n',
				sourceMap: false
			},

			dist: {
				files: {
					'<%= distFolder %>/css/<%= pkg.name %>.min.css' : ['<%= distFolder %>/css/<%= pkg.name %>.css']
				}
			}
		},

		uglify: {
			options: {
				banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd hh-MM-ss") %> */',
				sourceMap: false,
				mangle: false
			},
			dist: {
				src: ['<%= personalScriptFiles %>'],
				dest: '<%= distFolder %>/js/<%= pkg.name %>.min.js'
			}
		},

		htmlbuild: {
			dist: {
				src: 'public/index.html',
				dest: '<%= distFolder %>',
				relative: true,
				options: {
					beautify: false,
					relative: true,
					logOptions: true,

					scripts: {
						bundle: {
							cwd: '<%= distFolder %>',
							files: ['<%= vendorScriptFiles %>', 'js/<%= pkg.name %>.min.js']
						}
					},

					styles: {
						bundle: ['<%= distFolder %>/css/<%= pkg.name %>.min.css']
					}
				}
			},
		},

		watch: {
			options: {
				livereload: true
			}, 
			stylesheets: {
				files: 'public/less/*.less',
				tasks: ['clean:stylesheets', 'less:build']

			},

			scripts: {
				files: ['Gruntfile.js', 'public/js/**/*.js'],
				tasks: ['jshint:build']
			}
		}
	});

grunt.loadNpmTasks('grunt-contrib-watch');
grunt.loadNpmTasks('grunt-contrib-clean');
grunt.loadNpmTasks("grunt-contrib-copy");
grunt.loadNpmTasks('grunt-contrib-jshint');
grunt.loadNpmTasks('grunt-contrib-less');
grunt.loadNpmTasks('grunt-contrib-cssmin');
grunt.loadNpmTasks('grunt-contrib-uglify');
grunt.loadNpmTasks('grunt-html-build');

grunt.registerTask('watch-changes', ['watch']);
grunt.registerTask('default', ['dist']);
grunt.registerTask('dist', ['jshint:build','less:build', 'clean:dist', 'copy:dist', 'uglify:dist','cssmin:dist', 'htmlbuild:dist' ]);

};