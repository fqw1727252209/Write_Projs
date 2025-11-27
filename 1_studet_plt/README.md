# 在校学生个人学习规划平台

## 📚 项目简介

这是一个基于 Spring Boot + Vue + MyBatis 的前后端分离项目，旨在帮助在校大学生更好地规划和管理自己的学习任务、课程安排和学习目标。

## 🛠️ 技术栈

### 后端技术
- Spring Boot 2.7.18
- MyBatis 2.3.1
- MySQL 8.0
- Maven 3.6+
- JDK 1.8

### 前端技术
- Vue 3.3.4
- Element Plus 2.4.2
- Axios 1.6.0
- Vue Router 4.2.5
- ECharts 5.4.3

## 📁 项目结构

```
student-study-platform/
├── backend/                    # 后端项目
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/
│   │       │       └── study/
│   │       │           └── platform/
│   │       │               ├── controller/      # 控制器层
│   │       │               ├── service/         # 业务逻辑层
│   │       │               ├── mapper/          # 数据访问层
│   │       │               ├── entity/          # 实体类
│   │       │               ├── dto/             # 数据传输对象
│   │       │               ├── common/          # 公共类
│   │       │               └── config/          # 配置类
│   │       └── resources/
│   │           ├── mapper/                      # MyBatis映射文件
│   │           ├── application.yml              # 配置文件
│   │           └── application-dev.yml          # 开发环境配置
│   └── pom.xml                                  # Maven配置
│
├── frontend/                   # 前端项目
│   ├── src/
│   │   ├── api/                # API接口
│   │   ├── components/         # 公共组件
│   │   ├── views/              # 页面
│   │   ├── router/             # 路由配置
│   │   ├── store/              # 状态管理
│   │   ├── utils/              # 工具类
│   │   ├── assets/             # 静态资源
│   │   ├── App.vue             # 根组件
│   │   └── main.js             # 入口文件
│   ├── public/
│   ├── package.json            # npm配置
│   └── vite.config.js          # Vite配置
│
├── sql/                        # 数据库脚本
│   ├── create_database.sql    # 创建数据库
│   ├── create_tables.sql      # 创建表结构
│   └── init_data.sql           # 初始化数据
│
├── docs/                       # 文档目录
│   └── 接口文档.md
│
├── 项目说明文档.md
└── README.md                   # 项目说明（本文件）
```

## 🚀 快速开始

### 前置要求

在开始之前，请确保你的电脑上已经安装了以下软件：

1. **JDK 1.8** - Java开发工具包
   - 下载地址：https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
   - 安装后在命令行输入 `java -version` 检查是否安装成功

2. **Maven 3.6+** - Java项目构建工具
   - 下载地址：https://maven.apache.org/download.cgi
   - 安装后在命令行输入 `mvn -version` 检查是否安装成功

3. **Node.js 16+** - JavaScript运行环境
   - 下载地址：https://nodejs.org/
   - 安装后在命令行输入 `node -v` 和 `npm -v` 检查是否安装成功

4. **MySQL 8.0** - 数据库
   - 下载地址：https://dev.mysql.com/downloads/mysql/
   - 确保MySQL服务已启动

5. **IDE工具（二选一）**
   - IntelliJ IDEA（推荐）：https://www.jetbrains.com/idea/
   - VS Code：https://code.visualstudio.com/

### 第一步：克隆/下载项目

如果你是从Git仓库克隆：
```bash
git clone <项目地址>
cd student-study-platform
```

如果你是下载的压缩包，解压后进入项目目录即可。

### 第二步：初始化数据库

1. **启动MySQL数据库**
   - 确保MySQL服务正在运行
   - 可以通过命令行或MySQL Workbench连接数据库

2. **执行SQL脚本**

   打开命令行，进入项目的 `sql` 目录：
   ```bash
   cd sql
   ```

   然后依次执行以下命令：
   ```bash
   # 方式1：使用命令行执行（推荐）
   mysql -uroot -proot --default-character-set=utf8mb4 -e "source create_database.sql"
   mysql -uroot -proot --default-character-set=utf8mb4 -e "source create_tables.sql"
   mysql -uroot -proot --default-character-set=utf8mb4 -e "source init_data.sql"
   ```

   或者：
   ```bash
   # 方式2：登录MySQL后执行
   mysql -uroot -proot
   source create_database.sql;
   source create_tables.sql;
   source init_data.sql;
   exit;
   ```

   或者：
   ```
   # 方式3：使用MySQL Workbench等图形化工具
   打开MySQL Workbench，连接到数据库后，依次打开并执行这三个SQL文件
   ```

3. **验证数据库创建成功**
   ```bash
   mysql -uroot -proot
   use student_study_platform;
   show tables;
   ```
   应该能看到6张表：user, study_plan, study_task, course, study_note, study_resource

### 第三步：启动后端项目

1. **进入后端项目目录**
   ```bash
   cd backend
   ```

2. **修改数据库配置（如果需要）**
   
   打开 `src/main/resources/application-dev.yml` 文件，确认数据库配置：
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/student_study_platform?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
       username: root
       password: root
   ```

3. **安装依赖并启动**

   使用Maven命令：
   ```bash
   # 清理并安装依赖
   mvn clean install
   
   # 启动项目
   mvn spring-boot:run
   ```

   或者在IDEA中：
   - 右键点击 `PlatformApplication.java`
   - 选择 `Run 'PlatformApplication'`

4. **验证后端启动成功**
   
   看到以下日志说明启动成功：
   ```
   Started PlatformApplication in X.XXX seconds
   ```
   
   后端服务运行在：http://localhost:8080

### 第四步：启动前端项目

1. **打开新的命令行窗口**，进入前端项目目录
   ```bash
   cd frontend
   ```

2. **安装依赖**
   ```bash
   npm install
   ```
   
   如果安装速度慢，可以使用淘宝镜像：
   ```bash
   npm install --registry=https://registry.npmmirror.com
   ```

3. **启动开发服务器**
   ```bash
   npm run dev
   ```

4. **访问前端页面**
   
   看到以下信息说明启动成功：
   ```
   VITE v4.x.x  ready in XXX ms
   
   ➜  Local:   http://localhost:5173/
   ➜  Network: use --host to expose
   ```
   
   在浏览器中打开：http://localhost:5173

### 第五步：开始使用

1. **注册账号**
   - 点击"注册"按钮
   - 填写学号、姓名、密码等信息
   - 点击"注册"完成账号创建

2. **登录系统**
   - 使用学号和密码登录
   - 或使用测试账号：学号 `2021001`，密码 `123456`

3. **体验功能**
   - 创建学习计划
   - 添加学习任务
   - 录入课程表
   - 记录学习笔记
   - 上传学习资源
   - 查看学习统计

## 📝 功能模块说明

### 1. 用户管理
- 用户注册、登录
- 个人信息管理
- 密码修改

### 2. 学习计划管理
- 创建学习计划
- 查看计划列表
- 修改和删除计划
- 标记计划完成状态

### 3. 学习任务管理
- 创建学习任务
- 任务优先级设置
- 任务进度跟踪
- 任务完成统计

### 4. 课程表管理
- 添加课程信息
- 周课程表展示
- 课程信息管理

### 5. 学习统计
- 计划完成率统计
- 任务完成情况分析
- 数据可视化展示

### 6. 学习笔记管理
- 创建学习笔记
- 笔记分类管理
- 笔记搜索和收藏

### 7. 学习资源管理
- 上传学习资源
- 资源分类管理
- 资源下载

## 🔧 常见问题

### 1. 后端启动失败

**问题：端口被占用**
```
Port 8080 was already in use
```
解决方法：
- 修改 `application-dev.yml` 中的端口号
- 或者关闭占用8080端口的程序

**问题：数据库连接失败**
```
Cannot connect to database
```
解决方法：
- 检查MySQL服务是否启动
- 检查数据库账号密码是否正确
- 检查数据库是否已创建

### 2. 前端启动失败

**问题：依赖安装失败**
```
npm ERR! code ECONNREFUSED
```
解决方法：
- 使用淘宝镜像：`npm install --registry=https://registry.npmmirror.com`
- 或者使用cnpm：`npm install -g cnpm --registry=https://registry.npmmirror.com`

**问题：端口被占用**
```
Port 5173 is already in use
```
解决方法：
- Vite会自动使用下一个可用端口
- 或者在 `vite.config.js` 中修改端口号

### 3. 跨域问题

如果前端访问后端接口出现跨域错误，后端已经配置了CORS，如果还有问题：
- 检查后端的 `CorsConfig.java` 配置
- 确保前端的API请求地址正确

### 4. 文件上传失败

确保后端的文件上传目录存在且有写入权限：
- Windows: `D:/upload/`
- Linux/Mac: `/var/upload/`

可以在 `application-dev.yml` 中修改上传路径。

## 📖 开发指南

### 添加新功能的步骤

1. **后端开发**
   - 在 `entity` 包中创建实体类
   - 在 `mapper` 包中创建Mapper接口
   - 在 `resources/mapper` 中创建XML映射文件
   - 在 `service` 包中创建Service接口和实现类
   - 在 `controller` 包中创建Controller类

2. **前端开发**
   - 在 `api` 目录中创建API接口文件
   - 在 `views` 目录中创建页面组件
   - 在 `router` 中添加路由配置
   - 在页面中调用API接口

### 代码规范

- Java代码遵循阿里巴巴Java开发规范
- 前端代码遵循Vue官方风格指南
- 变量命名使用驼峰命名法
- 类名使用大驼峰，方法名使用小驼峰
- 每个方法都要添加注释说明

## 📞 技术支持

如果在使用过程中遇到问题：

1. 查看本README文档的"常见问题"部分
2. 查看项目的 `docs` 目录中的详细文档
3. 检查控制台的错误信息
4. 搜索相关技术文档

## 📄 许可证

本项目仅用于学习和毕业设计，请勿用于商业用途。

## 🎓 项目说明

这是一个大学生毕业设计项目，实现了在校学生个人学习规划平台的核心功能。通过本项目可以学习到：

- Spring Boot框架的使用
- Vue 3前端开发
- MyBatis数据持久化
- 前后端分离架构
- RESTful API设计
- MySQL数据库设计

---

**祝你使用愉快！如有问题，请仔细阅读文档或查看代码注释。** 🎉

