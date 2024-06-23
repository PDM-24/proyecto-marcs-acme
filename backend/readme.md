Aquí tienes la documentación en formato Markdown para las rutas utilizadas desde `adminRoutes`, `loginRoutes`, los modelos y controladores:

---

# API Documentation

## Environment Configuration

### .env
```env
MONGO_URI=mongodb+srv://00163922:FiUrIsXGNlapFWx4@taller2moviles.mdu23gk.mongodb.net/?retryWrites=true&w=majority&appName=taller2Moviles
PORT=3000
JWT_SECRET=proyectoMoviles
```
### .env
El archivo `.env` contiene las variables de entorno utilizadas en la aplicación, incluyendo la URI de la base de datos de MongoDB, el puerto en el que la aplicación escucha y el secreto JWT para la autenticación. Las variables de entorno permiten separar la configuración del código y facilitar cambios sin modificar el código fuente.

### app.js
Este archivo es el punto de entrada principal de la aplicación Express. Configura y conecta la base de datos MongoDB, establece los middlewares necesarios para el manejo de JSON y datos URL codificados, y define las rutas de la API para usuarios, administradores, cultivos, datos de sensores, registros de plagas y autenticación. Finalmente, inicia el servidor en el puerto especificado en el archivo `.env`.

### encryptPasswords.js
Este script se utiliza para cifrar las contraseñas de los usuarios existentes en la base de datos que aún no están cifradas. Primero, se conecta a la base de datos MongoDB, luego recupera todos los usuarios y cifra las contraseñas en texto plano usando `bcrypt`. Esto es útil para migrar una base de datos antigua a un formato más seguro.

### Rutas de la API

#### adminRoutes.js
Define las rutas específicas para las operaciones relacionadas con los administradores. Incluye rutas para obtener todos los administradores, obtener datos de gráficas, alertar a los usuarios, agregar un nuevo usuario, obtener todos los usuarios, actualizar un usuario por ID y eliminar un usuario por ID.

#### loginRoutes.js
Define la ruta para el proceso de inicio de sesión de los usuarios. Incluye una ruta POST para autenticar a los usuarios utilizando su nombre de usuario y contraseña.

### Modelos

#### admin.js
Define el esquema y modelo de los administradores en MongoDB utilizando Mongoose. Incluye campos para el nombre, contraseña y rol del administrador, con validaciones y configuración de timestamps para mantener un registro de creación y actualización.

#### user.js
Define el esquema y modelo de los usuarios en MongoDB utilizando Mongoose. Incluye campos para el nombre de usuario, contraseña y rol del usuario, con las validaciones necesarias.

### Controladores

#### adminController.js
Contiene la lógica para manejar las solicitudes relacionadas con los administradores. Incluye funciones para:

- **getAdmins:** Obtener todos los administradores.
- **getGraphs:** Obtener datos de gráficas (ejemplo simple).
- **alertUser:** Enviar alertas a los usuarios basadas en datos de sensores.
- **addUser:** Crear un nuevo usuario, cifrando la contraseña antes de guardarla.
- **getUsers:** Obtener todos los usuarios.
- **getUserById:** Obtener un usuario por su ID.
- **updateUser:** Actualizar los detalles de un usuario, incluyendo la posibilidad de actualizar la contraseña cifrada.
- **deleteUser:** Eliminar un usuario por su ID.

#### loginController.js
Contiene la lógica para manejar el inicio de sesión de los usuarios. Incluye una función principal:

- **login:** Autenticar a los usuarios comparando la contraseña ingresada con la almacenada en la base de datos utilizando `bcrypt`. Si la autenticación es exitosa, genera un token JWT.

#### userController.js
Contiene la lógica para manejar las solicitudes relacionadas con los usuarios. Incluye funciones para:

- **getUsers:** Obtener todos los usuarios.
- **getUserById:** Obtener un usuario por su ID.
- **addUser:** Crear un nuevo usuario.
- **updateUser:** Actualizar los detalles de un usuario.
- **deleteUser:** Eliminar un usuario por su ID.

Esta estructura modular y organizada facilita la gestión del código, el mantenimiento y la escalabilidad de la aplicación, permitiendo una separación clara de responsabilidades entre diferentes partes del sistema.
## Main Application

### app.js
```javascript
const express = require('express');
const bodyParser = require('body-parser');
const connectDB = require('./config/db.config');
const userRoutes = require('./routes/userRoutes');
const adminRoutes = require('./routes/adminRoutes');
const cropRoutes = require('./routes/cropRoutes');
const sensorDataRoutes = require('./routes/sensorRoutes');
const pestLogRoutes = require('./routes/pestRoutes');
const loginRoutes = require('./routes/loginRoutes');
require('dotenv').config(); 

const app = express();
const port = process.env.PORT || 3000;

connectDB();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.use('/api/users', userRoutes);
app.use('/api/admins', adminRoutes);
app.use('/api/crops', cropRoutes);
app.use('/api/sensor-data', sensorDataRoutes);
app.use('/api/pest-logs', pestLogRoutes);
app.use('/api', loginRoutes);

app.listen(port, () => {
    console.log(`SmartGreen API listening at http://localhost:${port}`);
});
```

## Encrypt Passwords Script

### encryptPasswords.js
```javascript
const bcrypt = require('bcryptjs');
const mongoose = require('mongoose');
require('dotenv').config();

const User = require('./models/user');

const connectDB = async () => {
    try {
        await mongoose.connect(process.env.MONGO_URI, {
            useNewUrlParser: true,
            useUnifiedTopology: true
        });
        console.log('MongoDB connected');
    } catch (err) {
        console.error(err.message);
        process.exit(1);
    }
};

const encryptPasswords = async () => {
    await connectDB();
    const users = await User.find();
    for (const user of users) {
        // Skip already encrypted passwords
        if (!user.password.startsWith('$2a$')) {
            const hashedPassword = await bcrypt.hash(user.password.replace('$', ''), 10);
            user.password = hashedPassword;
            await user.save();
        }
    }
    console.log('Passwords encrypted');
    process.exit();
};

encryptPasswords();
```

## Routes

### adminRoutes.js
```javascript
const express = require('express');
const router = express.Router();
const { getAdmins, getGraphs, alertUser, addUser, getUsers, updateUser, deleteUser } = require('../controllers/adminController');

router.get('/admin', getAdmins);
router.get('/graphs', getGraphs);
router.post('/alert', alertUser);
router.post('/add/user', addUser);
router.get('/user', getUsers);
router.put('/user/:id', updateUser);
router.delete('/user/delete/:id', deleteUser);

module.exports = router;
```

### loginRoutes.js
```javascript
const express = require('express');
const router = express.Router();
const { login } = require('../controllers/loginController');

router.post('/login', login);

module.exports = router;
```

## Models

### admin.js
```javascript
const mongoose = require('mongoose');

const AdminSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
    },
    password: {
        type: String,
        required: true,
    },
    role: {
        type: String,
        default: 'admin'
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Admin', AdminSchema);
```

### user.js
```javascript
const mongoose = require('mongoose');

const UserSchema = new mongoose.Schema({
    username: { type: String, required: true },
    password: { type: String, required: true },
    role: { type: String, required: true }
});

module.exports = mongoose.model('User', UserSchema);
```

## Controllers

### adminController.js
```javascript
const bcrypt = require('bcryptjs');
const Admin = require('../models/admin');
const User = require('../models/user');

// Obtener todos los administradores
const getAdmins = async (req, res) => {
    try {
        const admins = await Admin.find();
        res.json(admins);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener administradores' });
    }
};

// Obtener datos de gráficas
const getGraphs = async (req, res) => {
    try {
        const graphsData = {
            humidity: [],
            temperature: [],
            timestamps: []
        };
        res.json(graphsData);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener datos de gráficas' });
    }
};

// Alertar al usuario basado en datos de sensores
const alertUser = (req, res) => {
    try {
        const alertMessage = {
            user: 'Farmer1',
            alert: 'High humidity detected in your field.'
        };
        res.json(alertMessage);
    } catch (error) {
        res.status(500).json({ message: 'Error al alertar al usuario' });
    }
};

// Crear un nuevo usuario
const addUser = async (req, res) => {
    try {
        const { username, password, role } = req.body;

        const salt = await bcrypt.genSalt(10);
        const hashedPassword = await bcrypt.hash(password, salt);

        const newUser = new User({
            username,
            password: hashedPassword,
            role
        });

        await newUser.save();

        res.status(201).json(newUser);
    } catch (error) {
        console.error(error);
        res.status(400).json({ message: 'Error al crear el usuario' });
    }
};

// Obtener todos los usuarios
const getUsers = async (req, res) => {
    try {
        const users = await User.find();
        res.json(users);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener usuarios' });
    }
};

// Obtener usuario por ID
const getUserById = async (req, res) => {
    try {
        const user = await User.findById(req.params.id);
        if (!user) {
            return res.status(404).json({ message: 'Usuario no encontrado' });
        }
        res.json(user);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener el usuario' });
    }
};

// Actualizar un usuario existente
const updateUser = async (req, res) => {
    try {
        const { id } = req.params;
        const { password, ...updateFields } = req.body;

        if (password) {
            const salt = await bcrypt.genSalt(10);
            updateFields.password = await bcrypt.hash(password, salt);
        }

        const updatedUser = await User.findByIdAndUpdate(id, updateFields, { new: true });
        if (!updatedUser) {
            return res.status(404).json({ message: 'Usuario no encontrado' });
        }
        res.json(updatedUser);
    } catch (error) {
        res.status(400).json({ message: 'Error al actualizar el usuario' });
    }
};

// Eliminar un usuario
const deleteUser = async (req, res) => {
    try {
        const { id } = req.params;
        const deletedUser = await User.findByIdAndDelete(id);
        if (!deletedUser) {
            return res.status(404).json({ message: 'Usuario no encontrado' });
        }
        res.json({ message: 'Usuario eliminado' });
    } catch (error) {
        res.status(500).json({ message: 'Error al eliminar el usuario' });
    }
};

module.exports = {
    getAdmins,
    getGraphs,
    alertUser,
    addUser,
    getUsers,
    getUserById,
    updateUser,
    deleteUser
};
```

### loginController.js
```javascript
const User = require('../models/user');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

const login = async (req, res) => {
    const { username, password } = req.body;

    try {
        const user = await User.findOne({ username });
        if (!user) {
            return res.status(401).json({ message: 'Credenciales inválidas' });
        }

        const isMatch = await bcrypt.compare(password, user.password);
        if (!isMatch) {
            return res.status(401).json({ message: 'Credenciales inválidas' });
        }

        const token = jwt.sign({ id: user._id, role: user.role }, process.env.JWT_SECRET, { expiresIn: '1h' });

        res.json({ token });
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: 'Error al iniciar sesión' });
    }
};

module.exports = {
    login
};
```

### userController.js
```javascript
const User = require('../models/user');

// Obtener todos los usuarios
const getUsers = async (req, res) => {
    try {
        const users = await User.find();
        res.json(users);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener usuarios' });
    }
};

// Obtener usuario por ID
const getUserById = async (req, res) => {
    try {
       

 const user = await User.findById(req.params.id);
        if (!user) {
            return res.status(404).json({ message: 'Usuario no encontrado' });
        }
        res.json(user);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener el usuario' });
    }
};

// Crear nuevo usuario
const addUser = async (req, res) => {
    try {
        const newUser = new User({
            username: req.body.username,
            password: req.body.password,
            role: req.body.role
        });
        await newUser.save();
        res.status(201).json(newUser);
    } catch (error) {
        res.status(400).json({ message: 'Error al crear el usuario' });
    }
};

// Actualizar usuario
const updateUser = async (req, res) => {
    try {
        const user = await User.findById(req.params.id);
        if (!user) {
            return res.status(404).json({ message: 'Usuario no encontrado' });
        }
        user.username = req.body.username || user.username;
        user.password = req.body.password || user.password;
        user.role = req.body.role || user.role;
        await user.save();
        res.json(user);
    } catch (error) {
        res.status(400).json({ message: 'Error al actualizar el usuario' });
    }
};

// Eliminar usuario
const deleteUser = async (req, res) => {
    try {
        const user = await User.findByIdAndDelete(req.params.id);
        if (!user) {
            return res.status(404).json({ message: 'Usuario no encontrado' });
        }
        res.json({ message: 'Usuario eliminado' });
    } catch (error) {
        res.status(500).json({ message: 'Error al eliminar el usuario' });
    }
};

module.exports = {
    getUsers,
    getUserById,
    addUser,
    updateUser,
    deleteUser
};
```

---