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
